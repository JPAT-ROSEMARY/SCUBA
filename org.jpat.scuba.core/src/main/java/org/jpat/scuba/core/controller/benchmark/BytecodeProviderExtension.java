package org.jpat.scuba.core.controller.benchmark;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.jpat.scuba.core.model.IExtension;
import org.jpat.scuba.core.model.benchmark.IArchiveInOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BytecodeProviderExtension implements IExtension, ThreadFactory, UncaughtExceptionHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(BytecodeProviderExtension.class);

    private final IArchiveInOut archiveExtractor;
    private List<IArchiveInOut> currentlyRunning;
    private ExecutorService executorService;

    public BytecodeProviderExtension(final IArchiveInOut extractor)
    {
        assert null != extractor : "Parameter 'extractor' of method 'BytecodeProviderExtension' must not be null";

        this.archiveExtractor = extractor;
    }

    @Override
    public void uncaughtException(final Thread thread, final Throwable throwable)
    {
        LOGGER.error("Exception caught during Bytecode extracting", throwable);
    }

    @Override
    public Thread newThread(final Runnable runnable)
    {
        assert null != runnable : "Parameter 'runnable' of method 'newThread' must not be null";

        final Thread thread = new Thread(runnable);
        thread.setName("Bytecode Provider");
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.setUncaughtExceptionHandler(this);
        return thread;
    }

    public void run()
    {
        this.run(null);
    }

    private void run(final IArchiveInOut archiveInOutWorker)
    {
        synchronized (this)
        {
            shutdown();

            assert null == this.currentlyRunning : "'currentlyRunning' in method 'run' must be null";
            assert null == this.executorService : "'executorService' in method 'run' must be null";

            this.currentlyRunning = new ArrayList<>(1);
            if (null == archiveInOutWorker)
            {
                this.currentlyRunning.add(this.archiveExtractor);
            }
            else
            {
                this.currentlyRunning.add(archiveInOutWorker);
            }
            this.executorService = Executors.newFixedThreadPool(this.currentlyRunning.size(), this);

            this.currentlyRunning.forEach(t -> {
                this.executorService.submit(t);
            });
        }
        shutdown();
    }

    public void performPostAnalysisJob(final IArchiveInOut simpleScubaJarPackager)
    {
        assert null != simpleScubaJarPackager : "Parameter 'simpleScubaJarPackager' of method 'performPostAnalysisJob' must not be null";

        run(simpleScubaJarPackager);
    }

    private void shutdown()
    {
        if (null != this.executorService)
        {
            this.executorService.shutdown();
            try
            {
                this.executorService.awaitTermination(1, TimeUnit.DAYS);
            }
            catch (InterruptedException e)
            {
                LOGGER.error("Exception caught during shutdown", e);
            }
            this.executorService = null;
            this.currentlyRunning = null;
        }
    }
}
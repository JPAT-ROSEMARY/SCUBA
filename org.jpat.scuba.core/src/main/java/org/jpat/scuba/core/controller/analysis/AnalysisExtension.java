package org.jpat.scuba.core.controller.analysis;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.jpat.scuba.core.model.Analysers;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IExtension;
import org.jpat.scuba.core.model.analysis.Analyser;
import org.jpat.scuba.core.model.analysis.CoreAnalyserId;
import org.jpat.scuba.core.model.analysis.IAnalyserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnalysisExtension implements IExtension, ThreadFactory, UncaughtExceptionHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisExtension.class);

    private final FANChallenge fanchallenge;
    private List<AnalysisAdapter> currentlyRunning;
    private ExecutorService executorService;

    public AnalysisExtension(final FANChallenge fanChallenge, final Set<IAnalyserId> analyserIds)
    {
        assert null != fanChallenge : "Parameter 'fanChallenge' of 'AnalysisExtension''s ctor must not be null";
        assert null != analyserIds : "Parameter 'analyserIds' of 'AnalysisExtension''s ctor must not be null";

        this.fanchallenge = fanChallenge;

        final Set<IAnalyserId> all = new LinkedHashSet<>(Arrays.asList(CoreAnalyserId.values()));
        if (!analyserIds.isEmpty())
        {
            final boolean success = all.addAll(analyserIds);

            assert success : "Duplicate analyser added";
        }
        all.forEach(t -> makeAnalyser(t));
    }

    private void makeAnalyser(final IAnalyserId analyserId)
    {
        final Analysers analysers = this.fanchallenge.getAnalysers();
        final Analyser nextAnalyser = new Analyser(analysers, analyserId);
        analysers.addChild(nextAnalyser);
    }

    public final void run()
    {
        synchronized (this)
        {
            shutdown();

            assert null == this.currentlyRunning : "'currentlyRunning' of method 'run' must be null";
            assert null == this.executorService : "'mexecutorService' of method 'run' must be null";

            this.currentlyRunning = new ArrayList<>();
            addAnalysisAdapters(this.currentlyRunning, this.fanchallenge);

            this.executorService = Executors.newFixedThreadPool(this.currentlyRunning.size(), this);
            this.currentlyRunning.forEach(t ->
            {
                this.executorService.submit(t);
            });
        }
        shutdown();
    }

    /**
     * 
     * This provides you with a configuration means to add your favourite analysis adapters - analysis and analysers.
     * 
     * @param adapters
     *          Analysis adapters which link the analysers with the point of execution
     *          
     * @param fanchallenge
     *          Principle SCUBA benchmark model
     */
    protected void addAnalysisAdapters(final List<AnalysisAdapter> adapters, final FANChallenge fanchallenge)
    {
        /**
         * A child who has analysis adapter(s) can override this method
         */

        /***TODO - future work***/
        //adapters.add(new ClassLevelDependencyAnalysisAdapter(this.fanchallenge));
        LOGGER.debug("Configure your analysis adapters - analysers & analysis here: " + this.toString());
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

    @Override
    public void uncaughtException(final Thread thread, final Throwable throwable)
    {
        LOGGER.error("Exception caught during analysis", throwable);
    }

    @Override
    public Thread newThread(final Runnable runnable)
    {
        assert null != runnable : "Parameter 'runnable' of method 'newThread' must not be null";

        final Thread thread = new Thread(runnable);
        thread.setName("Analysis");
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.setUncaughtExceptionHandler(this);
        return thread;
    }

    @Override
    public final String toString()
    {
        return "Means to configure & execute global analysers & analysis";
    }
}

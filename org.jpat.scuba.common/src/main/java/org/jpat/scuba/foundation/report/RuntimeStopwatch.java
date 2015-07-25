package org.jpat.scuba.foundation.report;

import org.jpat.scuba.common.util.IKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum RuntimeStopwatch implements IKeeper
{
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(RuntimeStopwatch.class);
    public static final String EMPTY_STRING = "";

    private static long start;

    private String task = RuntimeStopwatch.EMPTY_STRING;

    private void setTask(final String task)
    {
        this.task = task;
    }

    @Override
    public final void init(final String task)
    {
        synchronized (this)
        {
            this.setTask(task);
            setStartTimePoint();
            Runtime.getRuntime().addShutdownHook(new Thread()
            {
                @Override
                public final void run()
                {
                    reportResults();
                }
            });
        }
    }

    private static void setStartTimePoint()
    {
        RuntimeStopwatch.start = System.currentTimeMillis();
    }

    protected final void reportResults()
    {
        synchronized (this)
        {
            final long end = System.currentTimeMillis();
            final long result = end - RuntimeStopwatch.start;
            RuntimeStopwatch.LOGGER.info("\n\n\t[*] Took '{}' (ms) to {} \n", result, this.task);
        }
    }
}

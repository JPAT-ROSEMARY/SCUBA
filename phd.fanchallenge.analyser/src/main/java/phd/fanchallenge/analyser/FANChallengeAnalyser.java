package phd.fanchallenge.analyser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FANChallengeAnalyser
{
    private static final Logger LOGGER = LoggerFactory.getLogger(FANChallengeAnalyser.class);

    private static volatile int count = 0;

    public static void main(final String[] args)
    {
        /**It is just a fake body*/
        LOGGER.info(FANChallengeAnalyser.class.getCanonicalName());
    }

    public static void init()
    {
        synchronized (FANChallengeAnalyser.class)
        {
            Runtime.getRuntime().addShutdownHook(new Thread()
            {
                public final void run()
                {
                    reportResults();
                }
            });
        }
    }

    public static void incrementCount()
    {
        synchronized (FANChallengeAnalyser.class)
        {
            ++count;
        }
    }

    protected static void reportResults()
    {
        synchronized (FANChallengeAnalyser.class)
        {
            LOGGER.info("Number of '" + "interface-method-type invokations at runtime" + "' is '" + String.valueOf(count) + "'.");
        }
    }
}

package org.jpat.scuba.core.model.stats;

public final class Operations4Stats
{
    public static Integer incrementStatsEntry(final Integer target)
    {
        assert null != target : "Parameter 'target' of method 'incrementStatsEntry' must not be null";

        /*TODO why synchronised  ?*/
        synchronized (Operations4Stats.class)
        {
            return Integer.sum(target, 1);
        }
    }

    public static Integer incrementStatsEntry(final Integer target, final int countedSoFar)
    {
        assert null != target : "Parameter 'target' of method 'incrementStatsEntry' must not be null";

        synchronized (Operations4Stats.class)
        {
            return Integer.sum(target, countedSoFar);
        }
    }
}

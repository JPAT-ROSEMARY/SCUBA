package org.jpat.scuba.core.model.stats;

import org.jpat.scuba.core.model.analysis.ScubaAnalysisOutcome;

public abstract class ScubaStats extends ScubaAnalysisOutcome
{
    protected ScubaStats()
    {
        super();
    }

    @Override
    public String getAlias()
    {
        return this.getClass().getSimpleName();
    }
}


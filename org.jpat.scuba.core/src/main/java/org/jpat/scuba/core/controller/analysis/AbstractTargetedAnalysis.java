package org.jpat.scuba.core.controller.analysis;

import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.analysis.ScubaAnalysisOutcome;

public abstract class AbstractTargetedAnalysis
{
    protected final FANChallenge benchmark;
    protected ScubaAnalysisOutcome scubaAnalysisOutcome;

    protected AbstractTargetedAnalysis(final FANChallenge benchmark)
    {
        assert null != benchmark : "Parameter 'benchmark' of 'AbstractTargetedAnalysis''s ctor must not be null";

        this.benchmark = benchmark;
    }
}

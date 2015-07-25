package org.jpat.scuba.analysisprovider.statica.bcel.foundation;

import org.apache.bcel.generic.MethodGen;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IStatsProvider;
import org.jpat.scuba.core.model.stats.BytecodeStats;
import org.jpat.scuba.foundation.report.Counter;

public abstract class AbstractTargetedStats extends AbstractBCELTargetedAnalysis
{
    protected AbstractTargetedStats(final FANChallenge benchmark)
    {
        super(benchmark);
    }

    @Override
    public final MethodGen analyse(final MethodGen methodGen)
    {
        assert methodGen != null : "Parameter 'methodGen' of method 'analyse' must not be null";

        super.scubaAnalysisOutcome = super.benchmark.getExtension(IStatsProvider.class).getBytecodeStats();
        Counter.INSTANCE.incrementVisitedMethodsCount();
        ((BytecodeStats) this.scubaAnalysisOutcome).incrementVisitedMethodsCount();

        return super.analyse(methodGen);
    }
}

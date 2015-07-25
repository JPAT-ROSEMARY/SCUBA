package org.jpat.scuba.analysisprovider.statica.asm.scala.foundation;

import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IStatsProvider;
import org.jpat.scuba.core.model.stats.BytecodeStats;
import org.jpat.scuba.foundation.report.Counter;

import scala.tools.asm.MethodVisitor;

public abstract class AbstractTargetedStats extends AbstractASMScalaTargetedAnalysis
{
    protected AbstractTargetedStats(final FANChallenge benchmark)
    {
        super(benchmark);
    }

    @Override
    public void initialiseScubaOutputModel()
    {
        super.scubaAnalysisOutcome = super.benchmark.getExtension(IStatsProvider.class).getBytecodeStats();
    }

    @Override
    public MethodVisitor analyse(final MethodVisitor mv, final int opcode)
    {
        assert null != super.scubaAnalysisOutcome : "'super.scubaAnalysisOutcome' of method 'analyse' must not be null";

        return analyseInternal(mv, opcode);
    }

    public final void performSimpleStatsAnalysisOnBytecodeInstructions()
    {
        Counter.INSTANCE.incrementByteCodeInstructionsCounter();
        ((BytecodeStats) this.scubaAnalysisOutcome).incrementAnalysedBytecodeInst();
    }

    public final void performSimpleStatsAnalysisOnMethod()
    {
        Counter.INSTANCE.incrementVisitedMethodsCount();
        ((BytecodeStats) this.scubaAnalysisOutcome).incrementVisitedMethodsCount();
    }
}

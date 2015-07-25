package org.jpat.scuba.analysisprovider.statica.asm.foundation;

import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.stats.BytecodeStats;
import org.jpat.scuba.foundation.report.Counter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public final class InterfaceMethodInvokStats extends AbstractTargetedStats
{
    public InterfaceMethodInvokStats(final FANChallenge benchmark)
    {
        super(benchmark);
    }

    @Override
    protected MethodVisitor analyseInternal(final MethodVisitor mv, final int opcode)
    {
        if (Opcodes.INVOKEINTERFACE == opcode)
        {
            Counter.INSTANCE.incrementTargetedInvokationCounter();
        }

        ((BytecodeStats) this.scubaAnalysisOutcome).incrementTargetedArtifact(opcode);
        Counter.INSTANCE.incrementByteCodeInstructionsCounter();
        ((BytecodeStats) this.scubaAnalysisOutcome).incrementAnalysedBytecodeInst();

        return mv;
    }
}

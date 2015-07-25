package org.jpat.scuba.analysisprovider.statica.bcel.foundation;

import java.util.Arrays;
import java.util.List;

import org.apache.bcel.Constants;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.MethodGen;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.stats.BytecodeStats;
import org.jpat.scuba.foundation.report.Counter;

public final class InterfaceMethodInvokStats extends AbstractTargetedStats
{
    public InterfaceMethodInvokStats(final FANChallenge benchmark)
    {
        super(benchmark);
    }

    @Override
    public MethodGen analyseInternal(final MethodGen methodGen)
    {
        assert methodGen != null : "Parameter 'methodGen' of method 'analyseInternal' must not be null";

        if (!methodGen.getMethod().isAbstract() && null != methodGen.getInstructionList())
        {
            final List<InstructionHandle> instnsHandleList = Arrays.asList(methodGen.getInstructionList().getInstructionHandles());
            instnsHandleList.forEach(t -> analyseAsVisiting(t, methodGen));
            return methodGen;
        }
        return null;
    }

    /**
     * 
     * @param nextHandle
     * @param nextMethodGen
     *          will be used in future to record names or more detail on analysed method
     */
    private void analyseAsVisiting(final InstructionHandle nextHandle, final MethodGen nextMethodGen)
    {
        assert null != nextHandle : "Parameter 'nextHandle' of method 'analyseAsVisiting' must not be null";
        assert null != nextMethodGen : "Parameter 'nextMethodGen' of method 'analyseAsVisiting' must not be null";
        assert null != super.scubaAnalysisOutcome : "'super.scubaOutput4SingleType' of method 'analyseAsVisiting' must not be null";

        final short opcode = nextHandle.getInstruction().getOpcode();
        if (Constants.INVOKEINTERFACE == opcode)
        {
            Counter.INSTANCE.incrementTargetedInvokationCounter();
        }
        ((BytecodeStats) super.scubaAnalysisOutcome).incrementTargetedArtifact(opcode);

        Counter.INSTANCE.incrementByteCodeInstructionsCounter();
        ((BytecodeStats) super.scubaAnalysisOutcome).incrementAnalysedBytecodeInst();
    }
}

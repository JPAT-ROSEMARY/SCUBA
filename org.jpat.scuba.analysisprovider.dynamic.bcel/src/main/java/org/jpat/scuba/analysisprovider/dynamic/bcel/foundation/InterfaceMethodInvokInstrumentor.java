package org.jpat.scuba.analysisprovider.dynamic.bcel.foundation;

import java.util.Arrays;
import java.util.List;

import org.apache.bcel.Constants;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.Type;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.stats.BytecodeStats;
import org.jpat.scuba.foundation.report.Counter;

public final class InterfaceMethodInvokInstrumentor extends AbstractTargetedInstrumentation
{
    private static final String TYPE_TO_INST_KEY = "phd/fanchallenge/analyser/FANChallengeAnalyser";
    private static final String INC_COUNT_KEY = "incrementCount";
    private static final String MAIN = "main";
    private static final String INIT_KEY = "init";

    public InterfaceMethodInvokInstrumentor(final FANChallenge benchmark)
    {
        super(benchmark);
    }

    @Override
    public MethodGen analyseInternal(final MethodGen nextMethodGen)
    {
        if (!nextMethodGen.getMethod().isAbstract() && null != nextMethodGen.getInstructionList())
        {
            final List<InstructionHandle> instnsHandleList = Arrays.asList(nextMethodGen.getInstructionList().getInstructionHandles());
            instnsHandleList.forEach(t -> analyseAsVisiting(t, nextMethodGen));
            visitMainMethod(nextMethodGen);
            return nextMethodGen;
        }
        return null;

    }

    private void analyseAsVisiting(final InstructionHandle nextHandle, final MethodGen nextMethodGen)
    {
        assert null != nextHandle : "Parameter 'nextHandle' of method 'analyseAsVisiting' must not be null";
        assert null != super.scubaAnalysisOutcome : "'super.scubaAnalysisOutput' of method 'analyseAsVisiting' must not be null";

        insertTargetInstruction(nextMethodGen, nextHandle);
        final short opcode = nextHandle.getInstruction().getOpcode();
        if (Constants.INVOKEINTERFACE == opcode)
        {
            Counter.INSTANCE.incrementTargetedInvokationCounter();
        }
        ((BytecodeStats) super.scubaAnalysisOutcome).incrementTargetedArtifact(opcode);
        Counter.INSTANCE.incrementByteCodeInstructionsCounter();
        ((BytecodeStats) super.scubaAnalysisOutcome).incrementAnalysedBytecodeInst();
    }

    /** Be careful! StackMapTable is not re-calculated after instrumentation With JDK 7 the following logic works. However, with Java 8 , it has some
     * bugs. One bug with some jars happened and I replaced ' nextMethodGen.getInstructionList().getStart()' with '
     * nextMethodGen.getInstructionList().getEnd()' and it worked. Bear in mind that we are using BCEL-5.2 which has not been updated to consider Java
     * 1.8 yet. */
    @Override
    protected void visitMainMethod(final MethodGen nextMethodGen)
    {
        if (MAIN.equals(nextMethodGen.getName()))
        {
            nextMethodGen.getInstructionList().insert(
                    nextMethodGen.getInstructionList().getStart(),
                    new InstructionFactory(nextMethodGen.getConstantPool()).createInvoke(TYPE_TO_INST_KEY, INIT_KEY, Type.VOID, Type.NO_ARGS,
                            Constants.INVOKESTATIC));
        }
    }

    private static void insertTargetInstruction(final MethodGen methodGen, final InstructionHandle instructionHandle)
    {
        assert null != methodGen : "Parameter 'methodGen' of method 'insertTargetInstruction' must not be null";
        assert null != instructionHandle : "Parameter 'instructionHandle' of method 'insertTargetInstruction' must not be null";

        if (Constants.INVOKEINTERFACE == instructionHandle.getInstruction().getOpcode())
        {
            methodGen.getInstructionList().insert(
                    instructionHandle,
                    new InstructionFactory(methodGen.getConstantPool()).createInvoke(TYPE_TO_INST_KEY, INC_COUNT_KEY, Type.VOID, Type.NO_ARGS,
                            Constants.INVOKESTATIC));
        }
    }
}

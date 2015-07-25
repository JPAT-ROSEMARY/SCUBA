package org.jpat.scuba.analysisprovider.dynamic.asm.foundation;

import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.stats.BytecodeStats;
import org.jpat.scuba.foundation.report.Counter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public final class InterfaceMethodInvokInstrumentor extends AbstractTargetedInstrumentation
{
    private static final String VOID_METHOD_KEY = "()V";
    private static final String TYPE_TO_INST_KEY = "phd/fanchallenge/analyser/FANChallengeAnalyser";
    private static final String INC_COUNT_KEY = "incrementCount";
    private static final String MAIN = "main";
    private static final String INIT_KEY = "init";

    public InterfaceMethodInvokInstrumentor(final FANChallenge benchmark)
    {
        super(benchmark);
    }

    @Override
    protected MethodVisitor analyseInternal(final MethodVisitor mv, final int opcode)
    {
        if (opcode == Opcodes.INVOKEINTERFACE)
        {
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, TYPE_TO_INST_KEY, INC_COUNT_KEY, VOID_METHOD_KEY, false);
            Counter.INSTANCE.incrementTargetedInvokationCounter();
        }
        ((BytecodeStats) this.scubaAnalysisOutcome).incrementAnalysedBytecodeInst();
        Counter.INSTANCE.incrementByteCodeInstructionsCounter();
        ((BytecodeStats) this.scubaAnalysisOutcome).incrementTargetedArtifact(opcode);
        return mv;
    }

    @Override
    protected void visitMainMethod(final MethodVisitor mv, final String methodName)
    {
        if (MAIN.equals(methodName))
        {
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, TYPE_TO_INST_KEY, INIT_KEY, VOID_METHOD_KEY, false);
        }
    }
}

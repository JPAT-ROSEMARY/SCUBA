package org.jpat.scuba.analysisprovider.statica.asm.foundation;

import org.jpat.scuba.core.model.IMethodAnalyser;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public final class ASMMethodAnalyser extends MethodVisitor implements IMethodAnalyser
{
    protected final AbstractASMTargetedAnalysis targetedAnalysis;

    public ASMMethodAnalyser(final AbstractASMTargetedAnalysis targetedAnalysis)
    {
        super(Opcodes.ASM5);

        assert null != targetedAnalysis : "Parameter 'targetedAnalysis' of 'ASMMethodAnalyser''s ctor must not be null";

        this.targetedAnalysis = targetedAnalysis;
    }

    @Override
    public MethodVisitor execute(final Object obj, final String methodName)
    {
        assert null == obj : "Parameter 'obj' of method 'execute' must be null";

        this.targetedAnalysis.initialiseScubaOutputModel();

        return this;
    }

    @Override
    public void visitMethodInsn(final int opcode, final String owner, final String name, final String desc, final boolean itf)
    {
        ((AbstractASMTargetedAnalysis) this.targetedAnalysis).analyse(this.mv, opcode);
    }

    @Override
    public void visitInsn(final int opcode)
    {
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public void visitIntInsn(final int opcode, final int operand)
    {
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public void visitVarInsn(final int opcode, final int var)
    {
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public void visitTypeInsn(final int opcode, final String desc)
    {
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public void visitFieldInsn(final int opcode, final String owner, final String name, final String desc)
    {
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public void visitInvokeDynamicInsn(final String name, final String desc, final Handle bsm, final Object... bsmArgs)
    {
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public void visitJumpInsn(final int opcode, final Label label)
    {
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public void visitLdcInsn(final Object cst)
    {
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public void visitIincInsn(final int var, final int increment)
    {
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public void visitTableSwitchInsn(final int min, final int max, final Label dflt, final Label... labels)
    {
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public void visitLookupSwitchInsn(final Label dflt, final int[] keys, final Label[] labels)
    {
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public void visitMultiANewArrayInsn(final String desc, final int dims)
    {
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public void visitEnd()
    {
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnMethod();
    }
}
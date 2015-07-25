package org.jpat.scuba.analysisprovider.dynamic.asm.foundation;

import org.jpat.scuba.analysisprovider.statica.asm.foundation.AbstractASMTargetedAnalysis;
import org.jpat.scuba.analysisprovider.statica.asm.foundation.AbstractTargetedStats;
import org.jpat.scuba.core.model.IMethodAnalyser;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public abstract class AbstractASMMethodInstrumentor extends MethodVisitor implements IMethodAnalyser, Opcodes
{
    protected final AbstractASMTargetedAnalysis targetedAnalysis;
    protected String methodName;

    protected AbstractASMMethodInstrumentor(final AbstractASMTargetedAnalysis targetedAnalysis)
    {
        super(ASM5);

        assert null != targetedAnalysis : "Parameter 'targetedAnalysis' of 'AbstractASMMethodInstrumentor''s ctor must not be null";

        this.targetedAnalysis = targetedAnalysis;
    }

    protected AbstractASMMethodInstrumentor(final AbstractASMTargetedAnalysis targetedAnalysis, final MethodVisitor mvPassed, final String methodName)
    {
        super(ASM5, mvPassed);

        assert null != targetedAnalysis : "Parameter 'targetedAnalysis' of 'AbstractASMMethodInstrumentor''s ctor must not be null";
        assert null != mvPassed : "Parameter 'mvPassed' of 'AbstractASMMethodInstrumentor''s ctor must not be null";
        assert null != methodName && !methodName.isEmpty() : "Parameter 'methodName' of 'AbstractASMMethodInstrumentor''s ctor must not be empty";

        this.targetedAnalysis = targetedAnalysis;
        this.mv = mvPassed;
        this.methodName = methodName;

        this.targetedAnalysis.initialiseScubaOutputModel();
    }

    protected abstract MethodVisitor executeInternal(final MethodVisitor methodVisitor, final String methodName);

    @Override
    public final MethodVisitor execute(final Object obj, final String methodName)
    {
        if (obj instanceof MethodVisitor)
        {
            this.targetedAnalysis.initialiseScubaOutputModel();
            return (MethodVisitor) executeInternal((MethodVisitor) obj, methodName);
        }
        return null;
    }

    @Override
    public final void visitInsn(final int opcode)
    {
        this.mv.visitInsn(opcode);
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public final void visitIntInsn(final int opcode, final int operand)
    {
        super.mv.visitIntInsn(opcode, operand);
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public final void visitVarInsn(final int opcode, final int var)
    {
        super.mv.visitVarInsn(opcode, var);
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public final void visitTypeInsn(final int opcode, final String desc)
    {
        super.mv.visitTypeInsn(opcode, desc);
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public final void visitFieldInsn(final int opcode, final String owner, final String name, final String desc)
    {
        super.mv.visitFieldInsn(opcode, owner, name, desc);
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public final void visitInvokeDynamicInsn(final String name, final String desc, final Handle bsm, final Object... bsmArgs)
    {
        super.mv.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public final void visitJumpInsn(final int opcode, final Label label)
    {
        super.mv.visitJumpInsn(opcode, label);
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public final void visitLdcInsn(final Object cst)
    {
        super.mv.visitLdcInsn(cst);
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public final void visitIincInsn(final int var, final int increment)
    {
        super.mv.visitIincInsn(var, increment);
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public final void visitTableSwitchInsn(final int min, final int max, final Label dflt, final Label... labels)
    {
        super.mv.visitTableSwitchInsn(min, max, dflt, labels);
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public final void visitLookupSwitchInsn(final Label dflt, final int[] keys, final Label[] labels)
    {
        super.mv.visitLookupSwitchInsn(dflt, keys, labels);
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public final void visitMultiANewArrayInsn(final String desc, final int dims)
    {
        super.mv.visitMultiANewArrayInsn(desc, dims);
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnBytecodeInstructions();
    }

    @Override
    public final void visitEnd()
    {
        super.mv.visitEnd();
        ((AbstractTargetedStats) this.targetedAnalysis).performSimpleStatsAnalysisOnMethod();
    }
}

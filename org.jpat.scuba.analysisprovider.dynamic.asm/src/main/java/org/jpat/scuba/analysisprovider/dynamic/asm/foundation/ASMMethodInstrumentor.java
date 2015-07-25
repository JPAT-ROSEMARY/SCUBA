package org.jpat.scuba.analysisprovider.dynamic.asm.foundation;

import org.jpat.scuba.analysisprovider.statica.asm.foundation.AbstractASMTargetedAnalysis;
import org.objectweb.asm.MethodVisitor;

public final class ASMMethodInstrumentor extends AbstractASMMethodInstrumentor
{
    public ASMMethodInstrumentor(final AbstractASMTargetedAnalysis targetedAnalysis)
    {
        super(targetedAnalysis);
    }

    private ASMMethodInstrumentor(final AbstractASMTargetedAnalysis targetedAnalysis, final MethodVisitor mvPassed, final String methodName)
    {
        super(targetedAnalysis, mvPassed, methodName);
    }

    @Override
    protected MethodVisitor executeInternal(final MethodVisitor methodVisitor, final String methodName)
    {
        return new ASMMethodInstrumentor(this.targetedAnalysis, methodVisitor, methodName);
    }

    @Override
    public void visitMethodInsn(final int opcode, final String owner, final String name, final String desc, final boolean itf)
    {
        super.mv.visitMethodInsn(opcode, owner, name, desc, itf);
        this.targetedAnalysis.analyse(this.mv, opcode);
    }

    @Override
    public void visitCode()
    {
        super.mv.visitCode();
        ((AbstractTargetedInstrumentation) this.targetedAnalysis).visitMainMethod(super.mv, this.methodName);
    }
}

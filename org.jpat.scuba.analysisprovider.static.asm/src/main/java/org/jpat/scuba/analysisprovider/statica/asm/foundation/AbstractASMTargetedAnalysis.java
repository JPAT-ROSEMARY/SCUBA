package org.jpat.scuba.analysisprovider.statica.asm.foundation;

import org.jpat.scuba.core.controller.analysis.AbstractTargetedAnalysis;
import org.jpat.scuba.core.model.FANChallenge;
import org.objectweb.asm.MethodVisitor;

public abstract class AbstractASMTargetedAnalysis extends AbstractTargetedAnalysis
{
    protected AbstractASMTargetedAnalysis(final FANChallenge benchmark)
    {
        super(benchmark);
    }

    public abstract void initialiseScubaOutputModel();

    protected abstract MethodVisitor analyseInternal(final MethodVisitor mv, final int opcode);

    public abstract MethodVisitor analyse(final MethodVisitor mv, final int opcode);
}

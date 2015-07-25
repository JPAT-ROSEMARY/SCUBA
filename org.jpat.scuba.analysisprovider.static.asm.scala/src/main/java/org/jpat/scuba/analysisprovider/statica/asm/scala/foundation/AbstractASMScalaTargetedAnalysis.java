package org.jpat.scuba.analysisprovider.statica.asm.scala.foundation;

import org.jpat.scuba.core.controller.analysis.AbstractTargetedAnalysis;
import org.jpat.scuba.core.model.FANChallenge;

import scala.tools.asm.MethodVisitor;

public abstract class AbstractASMScalaTargetedAnalysis extends AbstractTargetedAnalysis
{
    protected AbstractASMScalaTargetedAnalysis(final FANChallenge benchmark)
    {
        super(benchmark);
    }

    public abstract void initialiseScubaOutputModel();

    protected abstract MethodVisitor analyseInternal(final MethodVisitor mv, final int opcode);

    public abstract MethodVisitor analyse(final MethodVisitor mv, final int opcode);

}

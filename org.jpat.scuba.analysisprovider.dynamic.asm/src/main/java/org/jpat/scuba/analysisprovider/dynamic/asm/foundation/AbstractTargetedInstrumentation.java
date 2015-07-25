package org.jpat.scuba.analysisprovider.dynamic.asm.foundation;

import org.jpat.scuba.analysisprovider.statica.asm.foundation.AbstractTargetedStats;
import org.jpat.scuba.core.model.FANChallenge;
import org.objectweb.asm.MethodVisitor;

public abstract class AbstractTargetedInstrumentation extends AbstractTargetedStats
{
    protected AbstractTargetedInstrumentation(final FANChallenge benchmark)
    {
        super(benchmark);
    }

    protected abstract void visitMainMethod(final MethodVisitor mv, final String methodName);
}

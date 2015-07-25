package org.jpat.scuba.analysisprovider.dynamic.bcel.foundation;

import org.apache.bcel.generic.MethodGen;
import org.jpat.scuba.analysisprovider.statica.bcel.foundation.AbstractTargetedStats;
import org.jpat.scuba.core.model.FANChallenge;

/**
 * For learning purposes and reuse we extend AbstractTargetedStats
 * For future work when more instrumentation features need to be added 
 * we may choose to make another hierarchy that extends AbstractTargetedAnalysis directly
 */
public abstract class AbstractTargetedInstrumentation extends AbstractTargetedStats
{
    protected AbstractTargetedInstrumentation(final FANChallenge benchmark)
    {
        super(benchmark);
    }

    protected abstract void visitMainMethod(final MethodGen methodGen);
}

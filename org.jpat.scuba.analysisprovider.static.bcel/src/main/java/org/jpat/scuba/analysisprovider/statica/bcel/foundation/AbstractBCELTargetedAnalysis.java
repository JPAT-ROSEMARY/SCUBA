package org.jpat.scuba.analysisprovider.statica.bcel.foundation;

import org.apache.bcel.generic.MethodGen;
import org.jpat.scuba.core.controller.analysis.AbstractTargetedAnalysis;
import org.jpat.scuba.core.model.FANChallenge;

public abstract class AbstractBCELTargetedAnalysis extends AbstractTargetedAnalysis
{
    protected AbstractBCELTargetedAnalysis(final FANChallenge benchmark)
    {
        super(benchmark);
    }

    protected abstract MethodGen analyseInternal(final MethodGen methodGen);

    public MethodGen analyse(final MethodGen methodGen)
    {
        assert methodGen != null : "Parameter 'methodGen' of method 'analyse' must not be null";

        return analyseInternal(methodGen);
    }
}

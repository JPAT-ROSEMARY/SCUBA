package org.jpat.scuba.analysisprovider.statica.bcel.foundation;

import org.jpat.scuba.core.controller.analysis.AbstractTargetedAnalysis;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IMethodAnalyser;

public abstract class AbstractBCELMethodAnalyser implements IMethodAnalyser
{
    protected final FANChallenge benchamrk;
    protected final AbstractTargetedAnalysis targetedAnalysis;

    protected AbstractBCELMethodAnalyser(final FANChallenge benchamrk, final AbstractTargetedAnalysis targetedAnalysis)
    {
        assert null != benchamrk : "Parameter 'benchamrk' of 'BCELMethodAnalyser''s ctor must not be null";
        assert null != targetedAnalysis : "Parameter 'targetedAnalysis' of 'AbstractBCELMethodAnalyser''s ctor must not be null";

        this.benchamrk = benchamrk;
        this.targetedAnalysis = targetedAnalysis;
    }
}
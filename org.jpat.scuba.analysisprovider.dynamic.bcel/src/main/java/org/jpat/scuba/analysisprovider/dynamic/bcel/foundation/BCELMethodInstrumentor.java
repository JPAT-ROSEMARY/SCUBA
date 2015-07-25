package org.jpat.scuba.analysisprovider.dynamic.bcel.foundation;

import org.jpat.scuba.analysisprovider.statica.bcel.foundation.AbstractBCELTargetedAnalysis;
import org.jpat.scuba.analysisprovider.statica.bcel.foundation.BCELMethodAnalyser;
import org.jpat.scuba.core.model.FANChallenge;

public class BCELMethodInstrumentor extends BCELMethodAnalyser
{
    public BCELMethodInstrumentor(final FANChallenge benchamrk, final AbstractBCELTargetedAnalysis targetedInstrumentation)
    {
        super(benchamrk, targetedInstrumentation);
    }
}
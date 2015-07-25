package org.jpat.scuba.analysisprovider.statica.bcel.foundation;

import org.apache.bcel.generic.MethodGen;
import org.jpat.scuba.core.model.FANChallenge;

public class BCELMethodAnalyser extends AbstractBCELMethodAnalyser
{
    public BCELMethodAnalyser(final FANChallenge benchamrk, final AbstractBCELTargetedAnalysis targetedAnalysis)
    {
        super(benchamrk, targetedAnalysis);
    }

    @Override
    public Object execute(final Object obj, final String methodName)
    {
        if (obj instanceof MethodGen)
        {
            final MethodGen nextMethodGen = (MethodGen) obj;
            ((AbstractBCELTargetedAnalysis) super.targetedAnalysis).analyse(nextMethodGen);
        }
        return new Object();
    }
}
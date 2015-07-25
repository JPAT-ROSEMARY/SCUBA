package org.jpat.scuba.analysisprovider.dynamic.bcel.foundation;

import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.MethodGen;
import org.jpat.scuba.analysisprovider.statica.bcel.foundation.BCELClassParser;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IMethodAnalyser;

public class BCELClassInstrumentor extends BCELClassParser
{
    public BCELClassInstrumentor(final FANChallenge parent, final IMethodAnalyser methodAnalyser)
    {
        super(parent, methodAnalyser);
    }

    @Override
    protected void pareseInternal(final ClassGen classGen, final Method nextMethod, final MethodGen nextMethodGen)
    {
        assert null != nextMethodGen : "Parameter 'nextMethodGen' of method 'specificAnalysisAdapterJob' must not be null";
        assert null != classGen : "Parameter 'classGen' of method 'specificAnalysisAdapterJob' must not be null";

        classGen.replaceMethod(nextMethod, nextMethodGen.getMethod());
    }
}

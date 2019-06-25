package org.jpat.scuba.analysisprovider.statica.bcel.foundation;

import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.MethodGen;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IClassParser;
import org.jpat.scuba.core.model.IMethodAnalyser;

public abstract class AbstractBCELClassParser implements IClassParser
{
    protected final FANChallenge benchmark;
    protected final IMethodAnalyser methodAnalyser;

    protected AbstractBCELClassParser(final FANChallenge benchmark, final IMethodAnalyser methodAnalyser)
    {
        assert null != benchmark : "Parameter 'benchmark' of 'AbstractBCELClassParser''s ctor must not be null";
        assert null != methodAnalyser : "Parameter 'methodAnalyser' of 'AbstractBCELClassParser''s ctor must not be null";

        this.benchmark = benchmark;
        this.methodAnalyser = methodAnalyser;
    }

    protected abstract void pareseInternal(final ClassGen classGen, final Method nextMethod, final MethodGen nextMethodGen);
}


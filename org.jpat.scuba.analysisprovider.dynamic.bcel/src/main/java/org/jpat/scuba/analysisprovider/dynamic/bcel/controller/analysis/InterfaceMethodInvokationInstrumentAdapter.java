package org.jpat.scuba.analysisprovider.dynamic.bcel.controller.analysis;

import org.jpat.scuba.analysisprovider.dynamic.bcel.foundation.BCELClassInstrumentor;
import org.jpat.scuba.analysisprovider.dynamic.bcel.foundation.BCELMethodInstrumentor;
import org.jpat.scuba.analysisprovider.dynamic.bcel.foundation.InterfaceMethodInvokInstrumentor;
import org.jpat.scuba.analysisprovider.dynamic.bcel.model.BCELInstrumentorId;
import org.jpat.scuba.analysisprovider.statica.bcel.controller.analysis.InterfaceMethodInvokBCELConfigurator;
import org.jpat.scuba.analysisprovider.statica.bcel.foundation.AbstractBCELTargetedAnalysis;
import org.jpat.scuba.core.controller.StatsProvider;
import org.jpat.scuba.core.controller.analysis.InstrumentationAdapter;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IMethodAnalyser;

public final class InterfaceMethodInvokationInstrumentAdapter extends InstrumentationAdapter
{
    public InterfaceMethodInvokationInstrumentAdapter(final FANChallenge fanChallenge)
    {
        super(fanChallenge, BCELInstrumentorId.INTERFACE_METHOD_INVOKATION_INSTRUMENTOR, new InterfaceMethodInvokInstrumentor(fanChallenge));
        getFANChallenge().getExtension(StatsProvider.class).configureStatsExtension(new InterfaceMethodInvokBCELConfigurator());
    }

    @Override
    protected void internalRun()
    {
        final IMethodAnalyser methodInstrumentor = new BCELMethodInstrumentor(getFANChallenge(), (AbstractBCELTargetedAnalysis) super.targetedAnalysis);
        super.classParser = new BCELClassInstrumentor(getFANChallenge(), methodInstrumentor);
    }
}

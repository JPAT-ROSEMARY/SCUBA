package org.jpat.scuba.analysisprovider.dynamic.asm.controller.analysis;

import org.jpat.scuba.analysisprovider.dynamic.asm.foundation.ASMClassInstrumentor;
import org.jpat.scuba.analysisprovider.dynamic.asm.foundation.ASMMethodInstrumentor;
import org.jpat.scuba.analysisprovider.dynamic.asm.foundation.InterfaceMethodInvokInstrumentor;
import org.jpat.scuba.analysisprovider.dynamic.asm.model.ASMInstrumentorId;
import org.jpat.scuba.analysisprovider.statica.asm.controller.analysis.InterfaceMethodInvokASMConfigurator;
import org.jpat.scuba.analysisprovider.statica.asm.foundation.AbstractASMTargetedAnalysis;
import org.jpat.scuba.core.controller.StatsProvider;
import org.jpat.scuba.core.controller.analysis.InstrumentationAdapter;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IMethodAnalyser;

public final class InterfaceMethodInvokationInstrumentAdapter extends InstrumentationAdapter
{
    public InterfaceMethodInvokationInstrumentAdapter(final FANChallenge fanChallenge)
    {
        super(fanChallenge, ASMInstrumentorId.INTERFACE_METHOD_INVOKATION_INSTRUMENTOR, new InterfaceMethodInvokInstrumentor(fanChallenge));
        getFANChallenge().getExtension(StatsProvider.class).configureStatsExtension(new InterfaceMethodInvokASMConfigurator());
    }

    @Override
    protected void internalRun()
    {
        final IMethodAnalyser methodInstrumentor = new ASMMethodInstrumentor((AbstractASMTargetedAnalysis) super.targetedAnalysis);
        super.classParser = new ASMClassInstrumentor(getFANChallenge(), methodInstrumentor);
    }
}

package org.jpat.scuba.analysisprovider.statica.asm.controller.analysis;

import org.jpat.scuba.analysisprovider.statica.asm.foundation.ASMClassParser;
import org.jpat.scuba.analysisprovider.statica.asm.foundation.ASMMethodAnalyser;
import org.jpat.scuba.analysisprovider.statica.asm.foundation.AbstractASMTargetedAnalysis;
import org.jpat.scuba.analysisprovider.statica.asm.foundation.InterfaceMethodInvokStats;
import org.jpat.scuba.analysisprovider.statica.asm.model.ASMAnalyserId;
import org.jpat.scuba.core.controller.StatsProvider;
import org.jpat.scuba.core.controller.analysis.StatsAnalysisAdapter;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IMethodAnalyser;

public final class InterfaceMethodInvokationStatsAdapter extends StatsAnalysisAdapter
{
    public InterfaceMethodInvokationStatsAdapter(final FANChallenge benchmark)
    {
        super(benchmark, ASMAnalyserId.INTERFACE_METHOD_INVOKATION_COUNTER, new InterfaceMethodInvokStats(benchmark));
        getFANChallenge().getExtension(StatsProvider.class).configureStatsExtension(new InterfaceMethodInvokASMConfigurator());
    }

    @Override
    protected void internalRun()
    {
        final IMethodAnalyser methodAnalyser = new ASMMethodAnalyser((AbstractASMTargetedAnalysis) super.targetedAnalysis);
        super.classParser = new ASMClassParser(getFANChallenge(), methodAnalyser);
    }
}
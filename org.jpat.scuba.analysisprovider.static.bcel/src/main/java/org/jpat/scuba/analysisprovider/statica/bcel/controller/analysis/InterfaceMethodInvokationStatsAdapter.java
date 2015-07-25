package org.jpat.scuba.analysisprovider.statica.bcel.controller.analysis;

import org.jpat.scuba.analysisprovider.statica.bcel.foundation.AbstractBCELTargetedAnalysis;
import org.jpat.scuba.analysisprovider.statica.bcel.foundation.BCELClassParser;
import org.jpat.scuba.analysisprovider.statica.bcel.foundation.BCELMethodAnalyser;
import org.jpat.scuba.analysisprovider.statica.bcel.foundation.InterfaceMethodInvokStats;
import org.jpat.scuba.analysisprovider.statica.bcel.model.BCELAnalyserId;
import org.jpat.scuba.core.controller.StatsProvider;
import org.jpat.scuba.core.controller.analysis.StatsAnalysisAdapter;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IMethodAnalyser;

public class InterfaceMethodInvokationStatsAdapter extends StatsAnalysisAdapter
{
    public InterfaceMethodInvokationStatsAdapter(final FANChallenge fanChallenge)
    {
        super(fanChallenge, BCELAnalyserId.INTERFACE_METHOD_INVOKATION_COUNTER, new InterfaceMethodInvokStats(fanChallenge));
        getFANChallenge().getExtension(StatsProvider.class).configureStatsExtension(new InterfaceMethodInvokBCELConfigurator());
    }

    @Override
    protected void internalRun()
    {
        final IMethodAnalyser methodAnalyser = new BCELMethodAnalyser(getFANChallenge(), (AbstractBCELTargetedAnalysis) super.targetedAnalysis);
        super.classParser = new BCELClassParser(getFANChallenge(), methodAnalyser);
    }
}

package org.jpat.scuba.analysisprovider.statica.asm.scala.controller.analysis;

import org.jpat.scuba.analysisprovider.statica.asm.scala.foundation.ASMScalaClassParser;
import org.jpat.scuba.analysisprovider.statica.asm.scala.foundation.ASMScalaMethodAnalyser;
import org.jpat.scuba.analysisprovider.statica.asm.scala.foundation.AbstractASMScalaTargetedAnalysis;
import org.jpat.scuba.analysisprovider.statica.asm.scala.foundation.InterfaceMethodInvokStats;
import org.jpat.scuba.analysisprovider.statica.asm.scala.model.ASMScalaAnalyserId;
import org.jpat.scuba.core.controller.StatsProvider;
import org.jpat.scuba.core.controller.analysis.StatsAnalysisAdapter;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IMethodAnalyser;

public final class InterfaceMethodInvokScalaStatsAdapter extends StatsAnalysisAdapter
{
    public InterfaceMethodInvokScalaStatsAdapter(final FANChallenge fanChallenge)
    {
        super(fanChallenge, ASMScalaAnalyserId.INTERFACE_METHOD_INVOKATION_COUNTER, new InterfaceMethodInvokStats(fanChallenge));
        getFANChallenge().getExtension(StatsProvider.class).configureStatsExtension(new InterfaceMethodInvokASMScalaConfigurator());
    }

    @Override
    protected void internalRun()
    {
        final IMethodAnalyser methodAnalyser = new ASMScalaMethodAnalyser((AbstractASMScalaTargetedAnalysis) super.targetedAnalysis);
        super.classParser = new ASMScalaClassParser(getFANChallenge(), methodAnalyser);
    }
}

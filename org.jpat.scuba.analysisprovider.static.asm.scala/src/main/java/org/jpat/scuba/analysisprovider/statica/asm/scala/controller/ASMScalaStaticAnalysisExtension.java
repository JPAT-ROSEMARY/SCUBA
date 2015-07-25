package org.jpat.scuba.analysisprovider.statica.asm.scala.controller;

import java.util.List;
import java.util.Set;

import org.jpat.scuba.analysisprovider.statica.asm.scala.controller.analysis.InterfaceMethodInvokScalaStatsAdapter;
import org.jpat.scuba.core.controller.analysis.AnalysisAdapter;
import org.jpat.scuba.core.controller.analysis.AnalysisExtension;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.analysis.IAnalyserId;

public final class ASMScalaStaticAnalysisExtension extends AnalysisExtension
{
    public ASMScalaStaticAnalysisExtension(final FANChallenge fanChallenge, final Set<IAnalyserId> analyserIds)
    {
        super(fanChallenge, analyserIds);
    }

    @Override
    protected void addAnalysisAdapters(final List<AnalysisAdapter> adapters, final FANChallenge fanchallenge)
    {
        super.addAnalysisAdapters(adapters, fanchallenge);
        adapters.add(new InterfaceMethodInvokScalaStatsAdapter(fanchallenge));
    }
}

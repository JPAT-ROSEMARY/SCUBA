package org.jpat.scuba.analysisprovider.dynamic.bcel.controller;

import java.util.List;
import java.util.Set;

import org.jpat.scuba.analysisprovider.dynamic.bcel.controller.analysis.InterfaceMethodInvokationInstrumentAdapter;
import org.jpat.scuba.core.controller.analysis.AnalysisAdapter;
import org.jpat.scuba.core.controller.analysis.AnalysisExtension;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.analysis.IAnalyserId;

public final class BCELInstrumentExtension extends AnalysisExtension
{
    public BCELInstrumentExtension(final FANChallenge fanChallenge, final Set<IAnalyserId> analyserIds)
    {
        super(fanChallenge, analyserIds);
    }

    @Override
    protected void addAnalysisAdapters(final List<AnalysisAdapter> adapters, final FANChallenge fanchallenge)
    {
        super.addAnalysisAdapters(adapters, fanchallenge);
        adapters.add(new InterfaceMethodInvokationInstrumentAdapter(fanchallenge));
    }
}

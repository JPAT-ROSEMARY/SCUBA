package org.jpat.scuba.analysisprovider.dynamic.asm.controller;

import java.util.List;
import java.util.Set;

import org.jpat.scuba.analysisprovider.dynamic.asm.controller.analysis.InterfaceMethodInvokationInstrumentAdapter;
import org.jpat.scuba.core.controller.analysis.AnalysisAdapter;
import org.jpat.scuba.core.controller.analysis.AnalysisExtension;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.analysis.IAnalyserId;

public final class ASMInstrumentExtension extends AnalysisExtension
{
    public ASMInstrumentExtension(final FANChallenge fanChallenge, final Set<IAnalyserId> analyserIds)
    {
        super(fanChallenge, analyserIds);
    }

    /**
     * By default, the following analysis is configured. 
     * 
     */
    @Override
    protected void addAnalysisAdapters(final List<AnalysisAdapter> adapters, final FANChallenge fanchallenge)
    {
        super.addAnalysisAdapters(adapters, fanchallenge);
        adapters.add(new InterfaceMethodInvokationInstrumentAdapter(fanchallenge));
    }
}

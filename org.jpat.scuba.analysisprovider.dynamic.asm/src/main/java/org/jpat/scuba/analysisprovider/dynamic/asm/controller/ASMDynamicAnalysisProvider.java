package org.jpat.scuba.analysisprovider.dynamic.asm.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.jpat.scuba.analysisprovider.dynamic.asm.foundation.ASMDynamicAnalysis;
import org.jpat.scuba.analysisprovider.dynamic.asm.model.ASMInstrumentFANChallenge;
import org.jpat.scuba.analysisprovider.dynamic.asm.model.ASMInstrumentorId;
import org.jpat.scuba.core.controller.DynamicAnalysisProvider;
import org.jpat.scuba.core.controller.analysis.AnalysisExtension;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.analysis.IAnalyserId;
import org.jpat.scuba.support.languages.foundation.Java;

import de.schlichtherle.truezip.file.TFile;

/** By default the analysis provider and analysis types are configured with Java language, unless otherwise specified. */
public class ASMDynamicAnalysisProvider extends DynamicAnalysisProvider
{
    public ASMDynamicAnalysisProvider()
    {
        super(ASMDynamicAnalysis.INSTANCE, Java.INSTANCE);
    }

    @Override
    public Set<IAnalyserId> getAnalyserIds()
    {
        return new HashSet<IAnalyserId>(Arrays.asList(ASMInstrumentorId.values()));
    }

    @Override
    public FANChallenge createBenchmark(final TFile file, final String name)
    {
        return new ASMInstrumentFANChallenge(file, name);
    }

    @Override
    public AnalysisExtension createAnalysisExtension(final FANChallenge fanChallenge)
    {
        return new ASMInstrumentExtension(fanChallenge, getAnalyserIds());
    }
}

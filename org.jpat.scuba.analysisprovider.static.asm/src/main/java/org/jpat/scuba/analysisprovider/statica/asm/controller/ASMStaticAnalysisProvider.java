package org.jpat.scuba.analysisprovider.statica.asm.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.jpat.scuba.analysisprovider.statica.asm.foundation.ASMStaticAnalysis;
import org.jpat.scuba.analysisprovider.statica.asm.model.ASMAnalyserId;
import org.jpat.scuba.analysisprovider.statica.asm.model.ASMStaticFANChallenge;
import org.jpat.scuba.core.controller.StaticAnalysisProvider;
import org.jpat.scuba.core.controller.analysis.AnalysisExtension;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.analysis.IAnalyserId;
import org.jpat.scuba.support.languages.foundation.Java;

import de.schlichtherle.truezip.file.TFile;

/**
 * By default the analysis provider and analysis types are configured with Java language, unless otherwise specified.
 */
public final class ASMStaticAnalysisProvider extends StaticAnalysisProvider
{
    public ASMStaticAnalysisProvider()
    {
        super(ASMStaticAnalysis.INSTANCE, Java.INSTANCE);
    }

    @Override
    public Set<IAnalyserId> getAnalyserIds()
    {
        return new HashSet<IAnalyserId>(Arrays.asList(ASMAnalyserId.values()));
    }

    @Override
    public FANChallenge createBenchmark(final TFile file, final String name)
    {
        return new ASMStaticFANChallenge(file, name);
    }

    @Override
    public AnalysisExtension createAnalysisExtension(final FANChallenge fanChallenge)
    {
        return new ASMStaticAnalysisExtension(fanChallenge, getAnalyserIds());
    }
}

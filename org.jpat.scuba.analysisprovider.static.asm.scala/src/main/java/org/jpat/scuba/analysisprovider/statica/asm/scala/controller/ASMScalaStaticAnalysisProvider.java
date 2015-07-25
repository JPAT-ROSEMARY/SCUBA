package org.jpat.scuba.analysisprovider.statica.asm.scala.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.jpat.scuba.analysisprovider.statica.asm.scala.foundation.ASMScalaStaticAnalysis;
import org.jpat.scuba.analysisprovider.statica.asm.scala.model.ASMScalaAnalyserId;
import org.jpat.scuba.analysisprovider.statica.asm.scala.model.ASMScalaStaticFANChallenge;
import org.jpat.scuba.core.controller.StaticAnalysisProvider;
import org.jpat.scuba.core.controller.analysis.AnalysisExtension;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.analysis.IAnalyserId;
import org.jpat.scuba.support.languages.foundation.Scala;

import de.schlichtherle.truezip.file.TFile;

public final class ASMScalaStaticAnalysisProvider extends StaticAnalysisProvider
{
    public ASMScalaStaticAnalysisProvider()
    {
        super(ASMScalaStaticAnalysis.INSTANCE, Scala.INSTANCE);
    }

    @Override
    public Set<IAnalyserId> getAnalyserIds()
    {
        return new HashSet<IAnalyserId>(Arrays.asList(ASMScalaAnalyserId.values()));
    }

    @Override
    public FANChallenge createBenchmark(final TFile file, final String name)
    {
        return new ASMScalaStaticFANChallenge(file, name);
    }

    @Override
    public AnalysisExtension createAnalysisExtension(final FANChallenge fanChallenge)
    {
        return new ASMScalaStaticAnalysisExtension(fanChallenge, getAnalyserIds());
    }
}

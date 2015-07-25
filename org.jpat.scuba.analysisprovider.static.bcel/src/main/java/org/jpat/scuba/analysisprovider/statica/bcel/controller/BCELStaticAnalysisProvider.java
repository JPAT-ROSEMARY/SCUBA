package org.jpat.scuba.analysisprovider.statica.bcel.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.jpat.scuba.analysisprovider.statica.bcel.foundation.BCELStaticAnalysis;
import org.jpat.scuba.analysisprovider.statica.bcel.model.BCELAnalyserId;
import org.jpat.scuba.analysisprovider.statica.bcel.model.BCELStaticFANChallenge;
import org.jpat.scuba.core.controller.StaticAnalysisProvider;
import org.jpat.scuba.core.controller.analysis.AnalysisExtension;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.analysis.IAnalyserId;
import org.jpat.scuba.support.languages.foundation.Java;

import de.schlichtherle.truezip.file.TFile;

/**
 * By default the analysis provider and analysis types are configured with Java language, unless otherwise specified.
 */
public final class BCELStaticAnalysisProvider extends StaticAnalysisProvider
{
    public BCELStaticAnalysisProvider()
    {
        super(BCELStaticAnalysis.INSTANCE, Java.INSTANCE);
    }

    @Override
    public Set<IAnalyserId> getAnalyserIds()
    {
        return new HashSet<IAnalyserId>(Arrays.asList(BCELAnalyserId.values()));
    }

    @Override
    public FANChallenge createBenchmark(final TFile file, final String name)
    {
        return new BCELStaticFANChallenge(file, name);
    }

    @Override
    public AnalysisExtension createAnalysisExtension(final FANChallenge fanchallenge)
    {
        return new BCELStaticAnalysisExtension(fanchallenge, getAnalyserIds());
    }
}

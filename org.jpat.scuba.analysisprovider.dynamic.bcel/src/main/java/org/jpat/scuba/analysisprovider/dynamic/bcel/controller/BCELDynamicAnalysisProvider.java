package org.jpat.scuba.analysisprovider.dynamic.bcel.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.jpat.scuba.analysisprovider.dynamic.bcel.foundation.BCELDynamicAnalysis;
import org.jpat.scuba.analysisprovider.dynamic.bcel.model.BCELInstrumentFANChallenge;
import org.jpat.scuba.analysisprovider.dynamic.bcel.model.BCELInstrumentorId;
import org.jpat.scuba.core.controller.DynamicAnalysisProvider;
import org.jpat.scuba.core.controller.analysis.AnalysisExtension;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.analysis.IAnalyserId;
import org.jpat.scuba.support.languages.foundation.Java;

import de.schlichtherle.truezip.file.TFile;

/**
 * By default the analysis provider and analysis types are configured with Java language, unless otherwise specified.
 */
public final class BCELDynamicAnalysisProvider extends DynamicAnalysisProvider
{
    public BCELDynamicAnalysisProvider()
    {
        super(BCELDynamicAnalysis.INSTANCE, Java.INSTANCE);
    }

    @Override
    public Set<IAnalyserId> getAnalyserIds()
    {
        return new HashSet<IAnalyserId>(Arrays.asList(BCELInstrumentorId.values()));
    }

    @Override
    public FANChallenge createBenchmark(final TFile file, final String name)
    {
        return new BCELInstrumentFANChallenge(file, name);
    }

    @Override
    public AnalysisExtension createAnalysisExtension(final FANChallenge fanChallenge)
    {
        return new BCELInstrumentExtension(fanChallenge, getAnalyserIds());
    }
}

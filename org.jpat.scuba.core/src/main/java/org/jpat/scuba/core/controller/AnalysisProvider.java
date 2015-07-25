package org.jpat.scuba.core.controller;

import java.util.Collections;
import java.util.Set;

import org.jpat.scuba.core.controller.analysis.AnalysisExtension;
import org.jpat.scuba.core.foundation.Analysis;
import org.jpat.scuba.core.foundation.Language;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IAnalysisProvider;
import org.jpat.scuba.core.model.analysis.IAnalyserId;

import de.schlichtherle.truezip.file.TFile;

public abstract class AnalysisProvider implements IAnalysisProvider
{
    private final Analysis analysis;
    private final Language language;

    public AnalysisProvider(final Analysis analysis, final Language language)
    {
        assert null != analysis : "Parameter 'analysis' of 'AnalysisProvider''s ctor must not be null";
        assert null != language : "Parameter 'language' of 'AnalysisProvider''s ctor must not be null";

        this.analysis = analysis;
        this.language = language;
    }

    @Override
    public Analysis getAnalysis()
    {
        return this.analysis;
    }

    @Override
    public Language getLanguage()
    {
        return this.language;
    }

    @Override
    public Analysis deriveAnalysis(final String analysisProvider, final String analysisKind, final String language)
    {
        return this.analysis.resolve(analysisProvider, analysisKind, language);
    }

    @SuppressWarnings("static-method")
    public Set<IAnalyserId> getAnalyserIds()
    {
        /** children need to override*/
        return Collections.emptySet();
    }

    @SuppressWarnings("static-method")
    public FANChallenge createBenchmark(final TFile file, final String name)
    {
        return new FANChallenge(file, name);
    }

    public AnalysisExtension createAnalysisExtension(final FANChallenge benchmark)
    {
        return new AnalysisExtension(benchmark, getAnalyserIds());
    }
}
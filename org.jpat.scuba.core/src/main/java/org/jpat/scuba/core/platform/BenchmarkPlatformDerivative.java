package org.jpat.scuba.core.platform;

import java.util.List;

import org.jpat.scuba.common.util.FileUtil;
import org.jpat.scuba.common.util.StringUtil;
import org.jpat.scuba.core.foundation.Analysis;
import org.jpat.scuba.core.model.ExternalTreatmentChoice;
import org.jpat.scuba.core.model.IAnalysisProvider;
import org.jpat.scuba.core.model.IConfigurationProvider;
import org.jpat.scuba.foundation.exceptions.AnalysisConfigurationsNotSupported;

import de.schlichtherle.truezip.file.TFile;

public final class BenchmarkPlatformDerivative
{
    private static final String EXCLUDED = "excluded";

    @SuppressWarnings("unused") private final String title;
    private final List<IAnalysisProvider> analysisProviders;
    private final IConfigurationProvider benchmarkConfigurationProvider;

    public BenchmarkPlatformDerivative(final String title, final IConfigurationProvider benchmarkConfigurationProvider,
            final List<IAnalysisProvider> analysisProviders)
    {
        assert null != title && !title.isEmpty() : "Parameter 'title' of 'BenchmarkDerivative''s ctor must not be empty";
        assert null != benchmarkConfigurationProvider : "Parameter 'benchmarkConfigurationProvider' of 'BenchmarkDerivative''s ctor must not be null";
        assert null != analysisProviders : "Parameter 'analysisProviders' of 'BenchmarkDerivative''s ctor must not be null";
        assert !analysisProviders.isEmpty() : "Parameter 'analysisProviders' of 'BenchmarkDerivative''s ctor must not be empty";

        this.analysisProviders = analysisProviders;
        this.benchmarkConfigurationProvider = benchmarkConfigurationProvider;
        this.title = title;
    }

    public Analysis deriveAnalysis()
    {
        for (final IAnalysisProvider nextAnalysisProvider : this.analysisProviders)
        {
            final Analysis analysis = nextAnalysisProvider.deriveAnalysis(this.benchmarkConfigurationProvider.analysisProvider(),
                    this.benchmarkConfigurationProvider.analysisKind(), this.resolveLanguageChoice());
            if (null != analysis)
            {
                return analysis;
            }
        }
        throw new AnalysisConfigurationsNotSupported("SCUBA does not support '"
                + StringUtil.composeWithSpaceInBetween(this.benchmarkConfigurationProvider.analysisProvider(),
                        this.benchmarkConfigurationProvider.analysisKind(), this.resolveLanguageChoice()) + "' as analysis configurations");
    }

    public String getName()
    {
        final String benchmarkExampleName = this.benchmarkConfigurationProvider.benchmarkExampleName();
        if (StringUtil.isEmpty(benchmarkExampleName))
        {
            //FileUtil.extractFileNameWithoutDotExtension(getBenchmarkExampleBytecodeSourcePlace());
            return FileUtil.removeBuildVersionAndDotExtension(getBenchmarkExampleBytecodeSourcePlace());
        }
        return benchmarkExampleName;
    }

    public TFile getBenchmarkExampleBytecodeSourcePlace()
    {
        final String bytecodeArchivePath = this.benchmarkConfigurationProvider.bytecodePlacePath();
        if (null != bytecodeArchivePath)
        {
            return new TFile(bytecodeArchivePath);
        }
        return null;
    }

    public ExternalTreatmentChoice getExternalTreatmentChoice()
    {
        return null != this.benchmarkConfigurationProvider.externalTreatment() ? 0 == EXCLUDED
                .compareToIgnoreCase(this.benchmarkConfigurationProvider.externalTreatment().trim()) ? ExternalTreatmentChoice.EXCLUDED
                : ExternalTreatmentChoice.INCLUDED : ExternalTreatmentChoice.INCLUDED;
    }

    /**
     * @return 'java' in case of empty language preferences
     */
    private String resolveLanguageChoice()
    {
        final String languagePreferences = this.benchmarkConfigurationProvider.language();
        if (StringUtil.isEmpty(languagePreferences))
        {
            return "java";
        }
        else
        {
            return languagePreferences;
        }
    }
}

package org.jpat.scuba.core.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jpat.scuba.core.benchmark.archive.SimpleScubaJarPackager;
import org.jpat.scuba.core.controller.analysis.AnalysisExtension;
import org.jpat.scuba.core.controller.benchmark.BytecodeProviderExtension;
import org.jpat.scuba.core.foundation.Analysis;
import org.jpat.scuba.core.model.ExternalTreatmentChoice;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IAnalysisProvider;
import org.jpat.scuba.core.model.IBytecodeContainer;
import org.jpat.scuba.core.model.IBytecodeProvider;
import org.jpat.scuba.core.model.IScubaController;
import org.jpat.scuba.core.model.benchmark.BytecodeArtifactAnalysed;
import org.jpat.scuba.foundation.exceptions.NotArchiveException;

import de.schlichtherle.truezip.file.TFile;

public class ScubaController implements IScubaController
{
    private FANChallenge fanchallenge;
    private final Map<String, AnalysisProvider> analyserToAnalysisProvider = new LinkedHashMap<>(4);
    private boolean externalExcluded;

    public ScubaController(final Set<AnalysisProvider> specificAnalysisProviders)
    {
        assert null != specificAnalysisProviders : "Parameter 'specificAnalysisProviders' of 'ScubaController''s ctor must not be null";

        for (final AnalysisProvider nextAnalysisProvider : specificAnalysisProviders)
        {
            final Analysis analysis = nextAnalysisProvider.getAnalysis();
            if (null != analysis)
            {
                final AnalysisProvider previous = this.analyserToAnalysisProvider.put(analysis.getTechnicalValue(), nextAnalysisProvider);
                assert null == previous : "Already added analysis provider for analysis: " + analysis.getTechnicalValue();
            }
        }
    }

    @Override
    public final FANChallenge getFANChallenge()
    {
        assert null != this.fanchallenge : "'this.fanchallenge' in 'getFANChallenge''s ctor must not be null";

        return this.fanchallenge;
    }

    public final FANChallenge createBenchmark(final String analysisName, final TFile benchmarkBytecodeArchive, final String benchmarkArchiveName)
    {
        assert null != analysisName && !analysisName.isEmpty() : "Parameter 'analysisName' of method 'createBenchmark' must not be empty";
        assert null != benchmarkBytecodeArchive : "Parameter 'benchmarkBytecodeArchive' of method 'createBenchmark' must not be null";
        assert null != benchmarkArchiveName && !benchmarkArchiveName.isEmpty() : "Parameter 'benchmarkArchiveName' of method 'createBenchmark' must not be empty";

        if (!benchmarkBytecodeArchive.isArchive())
        {
            throw new NotArchiveException(benchmarkBytecodeArchive);
        }

        final AnalysisProvider analysisProvider = this.analyserToAnalysisProvider.get(analysisName);
        if (null != analysisProvider)
        {
            this.fanchallenge = analysisProvider.createBenchmark(benchmarkBytecodeArchive, benchmarkArchiveName);
            this.fanchallenge.addExtension(this);
            this.fanchallenge.setLanguage(analysisProvider.getLanguage());
            this.fanchallenge.setAnalysis(analysisProvider.getAnalysis());
            this.fanchallenge.addExtension(analysisProvider);
            final AnalysisExtension analysisExtension = analysisProvider.createAnalysisExtension(this.fanchallenge);
            this.fanchallenge.addExtension(analysisExtension);
            final StatsProvider statsProvider = new StatsProvider(this.fanchallenge);
            this.fanchallenge.addExtension(statsProvider);
            return this.fanchallenge;
        }
        return null;
    }

    public final void prepareBenchmark()
    {
        assert null != this.fanchallenge : "'fanchallenge' in method 'prepareBenchmark' must not be null";

        this.fanchallenge.getExtension(BytecodeProviderExtension.class).run();
    }

    public final void startSCUBAonBenchmark()
    {
        assert null != this.fanchallenge : "'fanchallenge' of method 'startSCUBAonBenchmark' must not be null";

        this.fanchallenge.getExtension(AnalysisExtension.class).run();

        postAnalysis();
    }

    protected final void postAnalysis()
    {
        if (!this.fanchallenge.getExtension(IAnalysisProvider.class).isPostAnalysisRequired())
        {
            return;
        }
        final String bytecodeContainerPath = this.fanchallenge.getBytecodeContainerPath();
        /**
         * No need to throw an exception if the archive is not Jar
         * only avoid to invoke the packup method is enough for the moment
         * 
         * if (!FileUtil.isJar(bytecodeContainerPath))
            {
                throw new ArchiveNotJarException("File '" + bytecodeContainerPath + "' is not a Jar archive");
            }
         */
        final List<BytecodeArtifactAnalysed> binaryArtifacts = getFANChallenge().getBytecodeArtifactsAnalysed();

        this.fanchallenge.getExtension(BytecodeProviderExtension.class).performPostAnalysisJob(
                new SimpleScubaJarPackager(binaryArtifacts, bytecodeContainerPath));
    }

    public final void setExternalExluded(final ExternalTreatmentChoice externalTreatmentChoice)
    {
        assert null != externalTreatmentChoice : "Parameter 'externalTreatmentChoice' of method 'setExternalExluded' must not be null";

        this.externalExcluded = externalTreatmentChoice == ExternalTreatmentChoice.EXCLUDED;
    }

    public final boolean isExternalExcluded()
    {
        return this.externalExcluded;
    }

    @Override
    public final void instantiateBytecodeProvider(final IBytecodeProvider bytecodeProvider)
    {
        assert null != bytecodeProvider : "Parameter 'bytecodeProvider' of method 'instantiateBytecodeProvider' must not be null";

        this.fanchallenge.addExtension(bytecodeProvider.createBytecodeProviderExtension());
    }

    @Override
    public final IBytecodeContainer getBytecodeContainer()
    {
        return this.getFANChallenge().getBytecodeContainer();
    }

    @Override
    public final TFile getBenchmarkExampleBytecodePlace()
    {
        return this.getFANChallenge().getArtifacts().getBenchmarkExampleBytecodePlace();
    }

    public void setFANChallenge(final FANChallenge fnachallege)
    {
        this.fanchallenge = fnachallege;
    }

    @Override
    public String getBenchmarkExampleName()
    {
        return getFANChallenge().getAlias();
    }
}

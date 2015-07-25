package org.jpat.scuba.core.foundation;

import java.util.Arrays;
import java.util.List;

import org.jpat.scuba.core.analysisprovider.statica.command.system.BadStaticAnalysisProviderCreator;
import org.jpat.scuba.core.controller.AnalysisProvider;
import org.jpat.scuba.core.model.IAnalysisProvider;

import de.schlichtherle.truezip.file.TFile;

public enum Constants
{
    $inst;

    public static final String TEST_BENCHMARK_EXAMPLE = "TEST_BENCHMARK_EXAMPLE";
    public static final TFile TEST_BENCHMARK_EXAMPLE_FILE = new TFile(Constants.TEST_BENCHMARK_EXAMPLE_PATH);
    private static final String TEST_BENCHMARK_EXAMPLE_PATH = "./src/test/resources/org/jpat/scuba/core/controller/phd.fanchallenge.analyser-1.0.0.jar";
    
    public static final AnalysisProvider ANALYSIS_PROVIDER = (AnalysisProvider) BadStaticAnalysisProviderCreator.create();
    public static final Analysis ANALYSIS = BadAnalysis.INSTANCE;
    public static final List<IAnalysisProvider> ANALYSIS_PROVIDERS = Arrays.asList(new IAnalysisProvider[] {Constants.ANALYSIS_PROVIDER });
}

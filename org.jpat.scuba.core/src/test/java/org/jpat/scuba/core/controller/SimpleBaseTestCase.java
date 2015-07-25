package org.jpat.scuba.core.controller;

import java.util.Arrays;
import java.util.List;

import org.jpat.scuba.core.analysisprovider.statica.command.system.BadStaticAnalysisProviderCreator;
import org.jpat.scuba.core.foundation.Analysis;
import org.jpat.scuba.core.foundation.BadAnalysis;
import org.jpat.scuba.core.model.IAnalysisProvider;

public abstract class SimpleBaseTestCase
{
    protected final AnalysisProvider analyserProvider;
    protected final Analysis analysis;
    protected final List<IAnalysisProvider> analysisProviders;

    protected SimpleBaseTestCase()
    {
        this.analyserProvider = (AnalysisProvider) BadStaticAnalysisProviderCreator.create();
        this.analysis = BadAnalysis.INSTANCE;
        this.analysisProviders = Arrays.asList(new IAnalysisProvider[] {this.analyserProvider });
    }
}

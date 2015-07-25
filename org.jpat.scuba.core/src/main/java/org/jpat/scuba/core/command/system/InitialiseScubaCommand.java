package org.jpat.scuba.core.command.system;

import org.jpat.scuba.common.util.StringUtil;
import org.jpat.scuba.core.controller.ScubaController;
import org.jpat.scuba.core.foundation.Analysis;
import org.jpat.scuba.core.model.ExternalTreatmentChoice;
import org.jpat.scuba.core.model.IScubaController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schlichtherle.truezip.file.TFile;

public final class InitialiseScubaCommand extends AbstractCommand
{
    private static final Logger LOGGER = LoggerFactory.getLogger(InitialiseScubaCommand.class);

    private final ScubaController scubaController;
    private final Analysis analysis;
    private final TFile benchmarkArchiveArtifact;
    private final String name;

    public InitialiseScubaCommand(final IScubaController controller, final Analysis analysis, final TFile benchmarkArchiveArtifact,
            final String name, final ExternalTreatmentChoice externalTreatmentChoice)
    {
        assert null != controller : "Parameter 'controller' of 'InitialiseScubaCommand''s ctor must not be null";
        assert null != analysis : "Parameter 'analysis' of 'InitialiseScubaCommand''s ctor must not be null";
        assert null != benchmarkArchiveArtifact : "Parameter 'benchmarkArchiveArtifact' of 'InitialiseScubaCommand''s ctor must not be null";
        assert null != externalTreatmentChoice : "Parameter 'externalTreatmentChoice' of 'InitialiseScubaCommand''s ctor must not be null";

        this.scubaController = (ScubaController) controller;
        this.analysis = analysis;
        this.benchmarkArchiveArtifact = benchmarkArchiveArtifact;
        this.name = name;
        this.scubaController.setExternalExluded(externalTreatmentChoice);
    }

    @Override
    protected void internalRun()
    {
        assert null != this.analysis : "'analyser' of method 'internalRun' must not be null";

        final String analysisTechnicalName = this.analysis.getTechnicalValue();

        LOGGER.info(StringUtil.compose("\n\n\t", "[*] Benchmark example: \'", this.name, "'", "\n", "->\t-> Your program will be analysed by ",
                analysisTechnicalName, " SCUBA"));

        this.scubaController.createBenchmark(analysisTechnicalName, this.benchmarkArchiveArtifact, this.name);
    }
}

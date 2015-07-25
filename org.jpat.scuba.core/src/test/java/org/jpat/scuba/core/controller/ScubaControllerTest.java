package org.jpat.scuba.core.controller;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jpat.scuba.core.command.system.InitialiseScubaCommand;
import org.jpat.scuba.core.foundation.Constants;
import org.jpat.scuba.core.model.ExternalTreatmentChoice;
import org.jpat.scuba.core.model.FANChallenge;
import org.junit.Before;
import org.junit.Test;

import de.schlichtherle.truezip.file.TFile;

public final class ScubaControllerTest extends SimpleBaseTestCase
{
    private ScubaController controller;
    private String name;
    private TFile bytecodeArchive;

    @Before
    public void setup()
    {
        this.name = Constants.TEST_BENCHMARK_EXAMPLE;
        this.bytecodeArchive = Constants.TEST_BENCHMARK_EXAMPLE_FILE;
        final Set<AnalysisProvider> analysisProviders = new HashSet<>(1);
        analysisProviders.add(super.analyserProvider);
        this.controller = new ScubaController(analysisProviders);
    }

    @Test
    public void testInitialiseScuba() throws IOException
    {
        new InitialiseScubaCommand(this.controller, super.analysis, this.bytecodeArchive, this.name, ExternalTreatmentChoice.EXCLUDED).run();
        final FANChallenge fanchallenge = this.controller.getFANChallenge();
        assertNotNull(fanchallenge);
    }
}

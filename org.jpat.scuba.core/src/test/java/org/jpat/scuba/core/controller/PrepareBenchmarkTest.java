package org.jpat.scuba.core.controller;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.jpat.scuba.core.command.system.InitialiseScubaCommand;
import org.jpat.scuba.core.command.system.PrepareBenchmarkCommand;
import org.jpat.scuba.core.command.system.ScubaControllerCreator;
import org.jpat.scuba.core.controller.analysis.MockitoSampleBaseTestCase;
import org.jpat.scuba.core.controller.benchmark.BytecodeProviderExtension;
import org.jpat.scuba.core.foundation.Constants;
import org.jpat.scuba.core.model.ExternalTreatmentChoice;
import org.jpat.scuba.core.model.IBytecodeProvider;
import org.jpat.scuba.core.model.IScubaController;
import org.jpat.scuba.core.model.benchmark.IArchiveInOut;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import de.schlichtherle.truezip.file.TFile;

public class PrepareBenchmarkTest extends MockitoSampleBaseTestCase
{
    @Mock protected IArchiveInOut archiveExtractor;
    private IBytecodeProvider bytecodeProvider;
    private IScubaController controller;
    private TFile file;
    private String name;
    private ExternalTreatmentChoice externalTreatmentChoice;

    @Before
    public void setup()
    {
        this.name = Constants.TEST_BENCHMARK_EXAMPLE;
        this.file = Constants.TEST_BENCHMARK_EXAMPLE_FILE;
        this.controller = ScubaControllerCreator.create(super.analysisProviders);
        this.externalTreatmentChoice = ExternalTreatmentChoice.EXCLUDED;
    }

    @Test
    public void test() throws IOException
    {
        new InitialiseScubaCommand(this.controller, super.analysis, this.file, this.name, this.externalTreatmentChoice).run();

        this.bytecodeProvider = new IBytecodeProvider()
        {
            @SuppressWarnings("unqualified-field-access")
            @Override
            public BytecodeProviderExtension createBytecodeProviderExtension()
            {
                return new BytecodeProviderExtension(archiveExtractor);
            }
        };

        this.controller.instantiateBytecodeProvider(this.bytecodeProvider);

        new PrepareBenchmarkCommand(this.controller).run();

        verify(this.archiveExtractor, only()).run();
    }
}

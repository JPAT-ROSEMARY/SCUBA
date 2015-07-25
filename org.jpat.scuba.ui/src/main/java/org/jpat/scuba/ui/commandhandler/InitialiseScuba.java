package org.jpat.scuba.ui.commandhandler;

import java.util.List;

import org.jpat.scuba.core.command.system.InitialiseScubaCommand;
import org.jpat.scuba.core.model.IAnalysisProvider;
import org.jpat.scuba.core.model.IConfigurationProvider;
import org.jpat.scuba.core.model.IScubaController;
import org.jpat.scuba.core.platform.BenchmarkPlatformDerivative;
import org.jpat.scuba.ui.app.BenchmarkRegistry;

public final class InitialiseScuba extends AbstractCommandHandler
{
    @Override
    public void execute()
    {
        final IScubaController controller = BenchmarkRegistry.getInstance().getController();

        final IConfigurationProvider configurationProvider = BenchmarkRegistry.getInstance().getConfigurationProvider();
        final List<IAnalysisProvider> analysisProviders = BenchmarkRegistry.getInstance().getAvailableAnalysisProviders();
        final BenchmarkPlatformDerivative derivative = new BenchmarkPlatformDerivative("Instantiate SCUBA Environment", configurationProvider,
                analysisProviders);

        final InitialiseScubaCommand cmd = new InitialiseScubaCommand(controller, derivative.deriveAnalysis(),
                derivative.getBenchmarkExampleBytecodeSourcePlace(), derivative.getName(), derivative.getExternalTreatmentChoice());

        cmd.run();
    }
}

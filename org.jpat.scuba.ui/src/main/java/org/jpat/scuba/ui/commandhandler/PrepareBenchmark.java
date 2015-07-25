package org.jpat.scuba.ui.commandhandler;

import org.jpat.scuba.core.command.system.PrepareBenchmarkCommand;
import org.jpat.scuba.core.model.IScubaController;
import org.jpat.scuba.ui.app.BenchmarkRegistry;

public final class PrepareBenchmark extends AbstractCommandHandler
{
    @Override
    public void execute()
    {
        final IScubaController controller = BenchmarkRegistry.getInstance().getController();
        BenchmarkRegistry.getInstance().instantiateBytecodeProvider();

        final PrepareBenchmarkCommand cmd = new PrepareBenchmarkCommand(controller);

        cmd.run();
    }
}

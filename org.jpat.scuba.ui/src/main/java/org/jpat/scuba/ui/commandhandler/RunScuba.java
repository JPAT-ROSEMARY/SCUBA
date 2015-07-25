package org.jpat.scuba.ui.commandhandler;

import org.jpat.scuba.core.command.system.RunScubaCommand;
import org.jpat.scuba.core.model.IScubaController;
import org.jpat.scuba.ui.app.BenchmarkRegistry;

public final class RunScuba extends AbstractCommandHandler
{
    @Override
    public void execute()
    {
        final IScubaController controller = BenchmarkRegistry.getInstance().getController();
        final RunScubaCommand cmd = new RunScubaCommand(controller);

        cmd.run();
    }
}

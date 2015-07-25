package org.jpat.scuba.core.command.system;

import org.jpat.scuba.core.controller.ScubaController;
import org.jpat.scuba.core.model.IScubaController;

public final class PrepareBenchmarkCommand extends AbstractCommand
{
    private final ScubaController scubaController;

    public PrepareBenchmarkCommand(final IScubaController controller)
    {
        assert null != controller : "Parameter 'controller' of 'PrepareBenchmarkCommand''s ctor must not be null";

        this.scubaController = (ScubaController) controller;

    }

    @Override
    protected void internalRun()
    {
        this.scubaController.prepareBenchmark();
    }
}

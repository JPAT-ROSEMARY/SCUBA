package org.jpat.scuba.core.command.system;

import org.jpat.scuba.core.controller.SimpleBytecodeProvider;
import org.jpat.scuba.core.model.IBytecodeProvider;
import org.jpat.scuba.core.model.IScubaController;

public final class BytecodeProviderCreator
{
    private BytecodeProviderCreator()
    {
        super();
    }

    public static IBytecodeProvider instantiate(final IScubaController controller)
    {
        return new SimpleBytecodeProvider(controller);
    }
}

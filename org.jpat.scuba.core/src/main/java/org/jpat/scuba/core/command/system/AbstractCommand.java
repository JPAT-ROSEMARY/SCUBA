package org.jpat.scuba.core.command.system;

public abstract class AbstractCommand
{
    protected abstract void internalRun();
    
    public final void run()
    {
        internalRun();
    }
}

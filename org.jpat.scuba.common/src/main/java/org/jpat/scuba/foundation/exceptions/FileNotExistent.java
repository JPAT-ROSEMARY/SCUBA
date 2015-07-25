package org.jpat.scuba.foundation.exceptions;

@SuppressWarnings("serial")
public final class FileNotExistent extends AbstractScubaRuntimeException
{
    public FileNotExistent()
    {
        super("Specified file does not exist");
    }
}

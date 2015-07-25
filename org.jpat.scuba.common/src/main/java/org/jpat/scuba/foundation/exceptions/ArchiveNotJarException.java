package org.jpat.scuba.foundation.exceptions;

@SuppressWarnings("serial")
public final class ArchiveNotJarException extends AbstractScubaRuntimeException
{
    public ArchiveNotJarException(final String message)
    {
        super(message);
    }
}

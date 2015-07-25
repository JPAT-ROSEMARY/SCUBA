package org.jpat.scuba.foundation.exceptions;

@SuppressWarnings("serial")
public final class EmptyStringException extends AbstractScubaRuntimeException
{
    public EmptyStringException()
    {
        super("String value passed should not be empty");
    }
}

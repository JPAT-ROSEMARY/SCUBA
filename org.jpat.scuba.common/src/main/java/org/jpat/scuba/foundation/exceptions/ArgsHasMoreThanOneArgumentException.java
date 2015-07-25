package org.jpat.scuba.foundation.exceptions;

@SuppressWarnings("serial")
public final class ArgsHasMoreThanOneArgumentException extends AbstractScubaRuntimeException
{
    public ArgsHasMoreThanOneArgumentException()
    {
        super("Should not have more than one argument passed");
    }
}

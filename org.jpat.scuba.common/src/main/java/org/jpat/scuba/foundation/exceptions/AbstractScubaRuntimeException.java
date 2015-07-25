package org.jpat.scuba.foundation.exceptions;

@SuppressWarnings("serial")
abstract class AbstractScubaRuntimeException extends RuntimeException
{
    protected AbstractScubaRuntimeException()
    {
        super();
    }

    protected AbstractScubaRuntimeException(final String message)
    {
        super(message);
    }

    protected AbstractScubaRuntimeException(final Throwable cause)
    {
        super(cause);
    }

    protected AbstractScubaRuntimeException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}

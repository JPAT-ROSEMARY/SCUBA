package org.jpat.scuba.foundation.exceptions;

@SuppressWarnings("serial")
public final class ScubaConfigurationException extends AbstractScubaRuntimeException
{
    public ScubaConfigurationException(final Throwable originalException)
    {
        super(originalException);
    }
}

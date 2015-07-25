package org.jpat.scuba.foundation.exceptions;

@SuppressWarnings("serial")
public final class ConfigurationProviderNotRegisteredException extends AbstractScubaRuntimeException
{
    public ConfigurationProviderNotRegisteredException()
    {
        super("SCUBA was not able to register a configuration provider");
    }
}

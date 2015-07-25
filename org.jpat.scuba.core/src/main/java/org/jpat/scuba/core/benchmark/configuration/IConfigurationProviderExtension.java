package org.jpat.scuba.core.benchmark.configuration;

import org.jpat.scuba.core.model.IConfigurationProvider;

public interface IConfigurationProviderExtension
{
    IConfigurationProvider getConfigurationProvider(final String configurationFilePath);
}

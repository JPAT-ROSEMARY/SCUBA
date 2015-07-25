package org.jpat.scuba.core.benchmark.configuration;

import java.util.Map;

import org.jpat.scuba.core.controller.DefaultConfigurationProvider;
import org.jpat.scuba.core.model.IConfigurationProvider;

public final class DefaultConfigurationProviderCreator
{
    private DefaultConfigurationProviderCreator()
    {
        super();
    }

    public static IConfigurationProvider create(final Map<String, Object> keysToValuesMap)
    {
        return new DefaultConfigurationProvider(keysToValuesMap);
    }
}

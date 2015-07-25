package org.jpat.scuba.core.controller;

import java.util.Map;

import org.jpat.scuba.core.model.IConfigurationProvider;

public class DefaultConfigurationProvider implements IConfigurationProvider
{
    private final Map<String, Object> keyToValueMap;

    public DefaultConfigurationProvider(final Map<String, Object> keyToValueMap)
    {
        assert null != keyToValueMap : "Parameter 'keyToValueMap' of 'DefaultConfigurationProvider''s ctor must not be null";

        this.keyToValueMap = keyToValueMap;
    }

    @Override
    public String analysisProvider()
    {
        return (String) this.keyToValueMap.get(KEY_PROVIDER);
    }

    @Override
    public String analysisKind()
    {
        return (String) this.keyToValueMap.get(KEY_ANALYSIS_KIND);
    }

    @Override
    public String benchmarkExampleName()
    {
        return (String) this.keyToValueMap.get(KEY_BENCHMARK_NAME);
    }

    @Override
    public String language()
    {
        return (String) this.keyToValueMap.get(KEY_LANGUAGE);
    }

    @Override
    public String bytecodePlacePath()
    {
        return (String) this.keyToValueMap.get(KEY_BINARY_ROOT_PATH);
    }

    @Override
    public boolean isReady()
    {
        return null != this.keyToValueMap && !this.keyToValueMap.isEmpty();
    }

    @Override
    public String externalTreatment()
    {
        return (String) this.keyToValueMap.get(KEY_EXTERNAL_TREATMENT);
    }
}

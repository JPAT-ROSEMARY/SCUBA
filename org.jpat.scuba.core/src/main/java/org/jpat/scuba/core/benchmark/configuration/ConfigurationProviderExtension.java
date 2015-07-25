package org.jpat.scuba.core.benchmark.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.jpat.scuba.core.model.IConfigurationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schlichtherle.truezip.file.TFile;
import de.schlichtherle.truezip.file.TFileInputStream;

public final class ConfigurationProviderExtension implements IConfigurationProviderExtension
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationProviderExtension.class);

    private IConfigurationProvider configurationProvider;

    public ConfigurationProviderExtension()
    {
        super();
    }

    @Override
    public IConfigurationProvider getConfigurationProvider(final String configurationFilePath)
    {
        assert null != configurationFilePath && !configurationFilePath.isEmpty() : "Parameter 'configurationFilePath' of method 'getConfigurationProvider' must not be empty"; //$NON-NLS-1$

        if (null == this.configurationProvider)
        {
            try (final TFileInputStream inStream = new TFileInputStream(new TFile(configurationFilePath));)
            {
                final Properties props = new Properties();
                props.load(inStream);
                final Map<String, Object> keysToValuesMap = new HashMap<>();
                for (final Iterator<Object> keysIter = props.keySet().iterator(); keysIter.hasNext();)
                {
                    final Object nextKey = keysIter.next();
                    if (nextKey instanceof String)
                    {
                        keysToValuesMap.put((String) nextKey, props.getProperty((String) nextKey));
                    }
                }
                this.configurationProvider = DefaultConfigurationProviderCreator.create(keysToValuesMap);
            }
            catch (IOException e)
            {
                LOGGER.error(e.toString());
            }
        }
        return this.configurationProvider;
    }
}

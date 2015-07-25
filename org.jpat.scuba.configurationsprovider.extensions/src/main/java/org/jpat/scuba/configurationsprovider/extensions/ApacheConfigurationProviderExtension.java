package org.jpat.scuba.configurationsprovider.extensions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.jpat.scuba.core.benchmark.configuration.DefaultConfigurationProviderCreator;
import org.jpat.scuba.core.benchmark.configuration.IConfigurationProviderExtension;
import org.jpat.scuba.core.model.IConfigurationProvider;
import org.jpat.scuba.foundation.exceptions.ScubaConfigurationException;

import de.schlichtherle.truezip.file.TFile;

public final class ApacheConfigurationProviderExtension implements IConfigurationProviderExtension
{
    private IConfigurationProvider configurationProvider;

    private PropertiesConfiguration propertiesConfiguration;

    public ApacheConfigurationProviderExtension()
    {
        super();
    }

    @Override
    public IConfigurationProvider getConfigurationProvider(final String resourceFilePath)
    {
        if (null == this.configurationProvider)
        {
            try
            {
                this.propertiesConfiguration = new PropertiesConfiguration(new TFile(resourceFilePath));
            }
            catch (ConfigurationException e)
            {
                throw new ScubaConfigurationException(e);
            }
            final Map<String, Object> keysToValuesMap = new HashMap<>();
            final Iterator<String> keysIterator = this.propertiesConfiguration.getKeys();
            while (keysIterator.hasNext())
            {
                final String nextKey = keysIterator.next();
                keysToValuesMap.put(nextKey, this.propertiesConfiguration.getString(nextKey));
            }
            this.configurationProvider = DefaultConfigurationProviderCreator.create(keysToValuesMap);
        }
        return this.configurationProvider;
    }
}

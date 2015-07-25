package org.jpat.scuba.core.platform;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.jpat.scuba.core.benchmark.configuration.IConfigurationValue;

public final class Platform
{
    private static ExtensionRegistry extensionRegistry;

    private Platform()
    {
        super();
    }

    public static ExtensionRegistry getExtensionRegistry()
    {
        if (null == extensionRegistry)
        {
            extensionRegistry = new ExtensionRegistry();
        }
        return extensionRegistry;
    }

    public static final class ExtensionRegistry
    {
        private final Map<String, Set<IConfigurationValue>> configurationItemMap = new LinkedHashMap<>(2);

        public void addConfigurationItem(final String key, final IConfigurationValue item)
        {
            assert null != key && !key.isEmpty() : "Parameter 'key' of method 'addConfigurationItem' must not be empty";
            assert null != item : "Parameter 'item' of method 'addConfigurationItem' must not be null";

            Set<IConfigurationValue> itemExistingSet = this.configurationItemMap.get(key);
            if (null == itemExistingSet)
            {
                itemExistingSet = new LinkedHashSet<IConfigurationValue>(1);
            }
            itemExistingSet.add(item);
            this.configurationItemMap.put(key, itemExistingSet);
        }

        public IConfigurationValue[] getConfigurationItem4(final String key)
        {
            return this.configurationItemMap.get(key).toArray(new IConfigurationValue[this.configurationItemMap.get(key).size()]);
        }
    }
}

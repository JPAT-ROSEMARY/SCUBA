package org.jpat.scuba.core.platform;

import org.jpat.scuba.core.benchmark.configuration.IConfigurationValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DefaultConfigurationValue implements IConfigurationValue
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultConfigurationValue.class);

    private final String configuredClassFullyQualiefiedName;

    public DefaultConfigurationValue(final String classFullyQuallifiedName)
    {
        assert null != classFullyQuallifiedName && !classFullyQuallifiedName.isEmpty() : "Parameter 'classFullyQuallifiedName' of 'DefaultConfigurationValue''s ctor must not be empty";

        this.configuredClassFullyQualiefiedName = classFullyQuallifiedName;
    }

    @Override
    public Object createExecutableExtension()
    {
        try
        {
            final Class<?> clazz = Class.forName(this.configuredClassFullyQualiefiedName);
            if (null != clazz)
            {
                return clazz.newInstance();
            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException e)
        {
            LOGGER.error(e.toString());
        }
        return null;
    }
}

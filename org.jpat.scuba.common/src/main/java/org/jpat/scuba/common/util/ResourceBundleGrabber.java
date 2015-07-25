package org.jpat.scuba.common.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public final class ResourceBundleGrabber
{
    private static final String BUNDLE_NAME = "org.jpat.scuba.foundation.io.messages";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private ResourceBundleGrabber()
    {
        super();
    }

    public static String getString(final String key)
    {
        try
        {
            return RESOURCE_BUNDLE.getString(key);
        }
        catch (MissingResourceException e)
        {
            return '!' + key + '$';
        }
    }
}

package org.jpat.scuba.common.util;

public class StringUtil
{
    private StringUtil()
    {
        super();
    }

    public static boolean assertNotEmpty(final String str)
    {
        return null != str && !str.isEmpty();
    }

    public static final String composeWithSpaceInBetween(final String value, final String... restVals)
    {
        assert null != value && !value.isEmpty() : "Parameter 'value' of method 'composeWithSpacesInBetween' must not be empty";

        final StringBuilder strBldr = new StringBuilder();
        strBldr.append(value);
        if (null != restVals && 0 < restVals.length)
        {
            for (final String nextVal : restVals)
            {
                strBldr.append(' ');
                strBldr.append(nextVal);
            }
        }
        strBldr.trimToSize();
        return strBldr.toString();
    }

    public static String compose(final String... strings)
    {
        final int size = strings.length;
        final StringBuilder strBldr = new StringBuilder();
        for (int i = 0; i < size; i++)
        {
            strBldr.append(strings[i]);
        }
        strBldr.trimToSize();
        return strBldr.toString();
    }

    public static boolean isEmpty(final String value)
    {
        return null == value || value.isEmpty();
    }
}

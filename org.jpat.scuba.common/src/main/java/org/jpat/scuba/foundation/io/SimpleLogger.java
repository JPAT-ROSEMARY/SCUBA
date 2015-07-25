package org.jpat.scuba.foundation.io;

import java.util.Arrays;

import org.slf4j.Logger;

// TODO remove this class
public final class SimpleLogger
{
    private SimpleLogger()
    {
        super();
    }

    public static void error(final Logger logger, final Exception exception, final Class<?> clazz, final String method, final String... extra)
    {
        final StringBuilder strBldr = SimpleLogger.composeMessage(exception, clazz, method, extra);
        logger.error(strBldr.toString());
    }

    private static StringBuilder composeMessage(final Exception exception, final Class<?> clazz, final String method, final String... extra)
    {
        final StringBuilder strBldr = new StringBuilder();
        strBldr.trimToSize();
        consumeExceptionDetail(exception, strBldr);
        strBldr.append("; Class ");
        strBldr.append(clazz);
        strBldr.append("; Method ");
        strBldr.append(method);
        consumeStringArray(strBldr, extra);
        strBldr.trimToSize();
        return strBldr;
    }

    private static void consumeStringArray(final StringBuilder strBldr, final String... extra)
    {
        final String[] passedValues = extra;
        Arrays.asList(passedValues).forEach(next -> {
            strBldr.append("; object: ");
            strBldr.append(next);
        });
    }

    private static void consumeExceptionDetail(final Exception exception, final StringBuilder strBldr)
    {
        strBldr.append(exception.getClass());
        strBldr.append("\nException name '");
        strBldr.append(exception.toString());
        strBldr.append("'; Message '");
        strBldr.append(exception.getMessage());
        strBldr.append("; Cause ");
        strBldr.append(exception.getCause());
    }
}

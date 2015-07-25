package org.jpat.scuba.common.util.args;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SimpleArgsValidator
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleArgsValidator.class);

    private static SimpleArgsValidator instance;

    private String[] args;

    private SimpleArgsValidator(final String[] args)
    {
        this.args = args;
    }

    public void runValidation()
    {
        ArgsStatus.fillUp(this.args);
        LOGGER.debug(ArgsStatus.ARGS_HEALTHY == ArgsStatus.getMapArgStatus2Value().get(true) ? "OK" : "Has not Passed");
    }

    public static SimpleArgsValidator getInstance(final String[] args)
    {
        if (null == instance)
        {
            instance = new SimpleArgsValidator(args);
        }
        return instance;
    }
}

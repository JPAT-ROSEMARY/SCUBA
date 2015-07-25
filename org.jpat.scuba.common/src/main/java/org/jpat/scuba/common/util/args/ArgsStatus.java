package org.jpat.scuba.common.util.args;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jpat.scuba.foundation.exceptions.ArgsHasMoreThanOneArgumentException;
import org.jpat.scuba.foundation.exceptions.EmptyStringException;
import org.jpat.scuba.foundation.exceptions.FileNotExistent;

import de.schlichtherle.truezip.file.TFile;

enum ArgsStatus
{
    NULL_OR_EMPTY(false, Constants.MSG_NULL_OR_EMPTY_ARGS, new EmptyStringException()),
    HAS_MORE_THAN_ONE_ARG(false, Constants.MSG_ARGS_HAS_MORE_THAN_ONE_ARGS, new ArgsHasMoreThanOneArgumentException()),
    ARG_VALUE_NOT_EXISTENT(false, Constants.MSG_ARGS_VALUE_NOT_EXISTENT, new FileNotExistent()),
    ARGS_HEALTHY(true, "HEALTHY", null);

    private static final HashMap<Boolean, ArgsStatus> MAP_ARGS_VALUE_2_STATUS = new HashMap<>(4);

    static void fillUp(final String[] args)
    {
        MAP_ARGS_VALUE_2_STATUS.put(null == args || 0 == args.length, NULL_OR_EMPTY);
        MAP_ARGS_VALUE_2_STATUS.put(null != args && 1 < args.length, HAS_MORE_THAN_ONE_ARG);
        MAP_ARGS_VALUE_2_STATUS.put(null != args && 1 == args.length && new TFile(args[0].trim()).exists(), ARGS_HEALTHY);
        MAP_ARGS_VALUE_2_STATUS.put(null != args && 1 == args.length && !new TFile(args[0].trim()).exists(), ARG_VALUE_NOT_EXISTENT);
    }

    private final boolean canPass;
    private final String returnMsg;
    private final Throwable exception;

    private ArgsStatus(final boolean canPass, final String returnMsg, final Throwable exception)
    {
        assert null != returnMsg && !returnMsg.isEmpty() : "Parameter 'returnMsg' of method 'ArgsStatus' must not be empty";

        this.canPass = canPass;
        this.returnMsg = returnMsg;
        this.exception = exception;
    }

    boolean hasPassed()
    {
        return this.canPass;
    }

    String getReturnMsg()
    {
        return this.returnMsg;
    }

    Throwable getExceptionIfAny()
    {
        return this.exception;
    }

    static Map<Boolean, ArgsStatus> getMapArgStatus2Value()
    {
        return Collections.unmodifiableMap(MAP_ARGS_VALUE_2_STATUS);
    }
}

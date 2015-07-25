package org.jpat.scuba.common.util.args;

public enum Constants
{
    inst;

    public static final String MSG_NULL_OR_EMPTY_ARGS = "\n\n\t(!) Expected one argument: 'a benchmark configuration file path'\n\tAnalyser has refused to start.\n";
    public static final String MSG_ARGS_VALUE_NOT_EXISTENT = "\n\n\t(!) The specified file path does not exist: '{}' \n\tAnalyser has refused to start.\n";
    public static final String MSG_ARGS_HAS_MORE_THAN_ONE_ARGS = "\n\n\t(!) Expected only one argument: 'a benchmark configuration file path'. \n\tAnalyser has refused to start.\n";
}

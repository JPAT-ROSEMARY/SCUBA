package org.jpat.scuba.core.model;

public interface IMethodAnalyser
{
    /**
     * July 6th, 2015.
     * WARNING: the first parameter type can make falls! Simply passing any potentially unexpected type variable will make a headache at dev time.
     * 
     * @param obj
     *        Analysis-Provider-specific model chosen at runtime
     * @param methodName
     *        subject method name being analysed
     * @return
     *      the analysed model if has been internally altered - useful for instrumentation
     */
    Object execute(final Object obj, final String methodName);
}

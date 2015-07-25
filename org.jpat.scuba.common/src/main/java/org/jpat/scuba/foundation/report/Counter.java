package org.jpat.scuba.foundation.report;

import org.jpat.scuba.common.util.IKeeper;
import org.jpat.scuba.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Counter implements IKeeper
{
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(Counter.class);

    private String task = "";
    private int interfaceMethodInvocationCounter;
    private int visitedMethodsCount;
    private int analysedTypesCounter;
    private int byteCodeInstructionsCounter;

    private void setTask(final String taskPassed)
    {
        this.task = taskPassed;
    }

    @Override
    public final void init(final String task)
    {
        synchronized (this)
        {
            setTask(task);
            Runtime.getRuntime().addShutdownHook(new Thread()
            {
                @Override
                public final void run()
                {
                    reportResults();
                }
            });
        }
    }

    protected final void reportResults()
    {
        synchronized (this)
        {
            final String visitedTypes = StringUtil.compose("\n\n\t[*] Visited types: '", String.valueOf(this.analysedTypesCounter), "'\n");
            final String interfaceMethodInvocations = StringUtil.compose("\n\t[*] ", this.task, ": '",
                    String.valueOf(this.interfaceMethodInvocationCounter), "'\n");
            final String visitedMethods = StringUtil.compose("\n\t[*] ", "Visited methods: '", String.valueOf(this.visitedMethodsCount), "'\n");
            final String byteCodeAnalysed = StringUtil.compose("\n\t[*] Analysed bytecode instructions: ", "'",
                    String.valueOf(this.byteCodeInstructionsCounter), "'");
            Counter.LOGGER.info(StringUtil.compose(visitedTypes, interfaceMethodInvocations, visitedMethods, byteCodeAnalysed, "\n"));
        }
    }

    public void incrementTargetedInvokationCounter()
    {
        synchronized (this)
        {
            ++this.interfaceMethodInvocationCounter;
        }
    }

    public void incrementVisitedMethodsCount()
    {
        synchronized (this)
        {
            ++this.visitedMethodsCount;
        }
    }

    public void incrementAnalysedTypesCounter()
    {
        synchronized (Counter.class)
        {
            ++this.analysedTypesCounter;
        }
    }

    public void incrementByteCodeInstructionsCounter()
    {
        synchronized (Counter.class)
        {
            ++this.byteCodeInstructionsCounter;
        }
    }

    public int visitedMethods()
    {
        synchronized (Counter.class)
        {
            return this.visitedMethodsCount;
        }
    }

    public int bytecodeInstnsStats()
    {
        return this.byteCodeInstructionsCounter;
    }
}

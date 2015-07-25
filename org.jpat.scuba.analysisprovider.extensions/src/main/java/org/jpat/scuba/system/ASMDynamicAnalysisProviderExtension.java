package org.jpat.scuba.system;

import org.jpat.scuba.analysisprovider.dynamic.asm.command.system.ASMDynamicAnalysisProviderCreater;
import org.jpat.scuba.core.command.system.IAnalysisProviderExtension;
import org.jpat.scuba.core.model.IAnalysisProvider;

public class ASMDynamicAnalysisProviderExtension implements IAnalysisProviderExtension
{
    private IAnalysisProvider analysisProvider;

    public ASMDynamicAnalysisProviderExtension()
    {
        super();
    }

    @Override
    public IAnalysisProvider getAnalysisProvider()
    {
        if (null == this.analysisProvider)
        {
            this.analysisProvider = ASMDynamicAnalysisProviderCreater.create();
        }
        return this.analysisProvider;
    }
}

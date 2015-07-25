package org.jpat.scuba.system;

import org.jpat.scuba.analysisprovider.statica.asm.command.system.ASMStaticAnalysisProviderCreator;
import org.jpat.scuba.core.command.system.IAnalysisProviderExtension;
import org.jpat.scuba.core.model.IAnalysisProvider;

public final class ASMStaticAnalysisProviderExtension implements IAnalysisProviderExtension
{
    private IAnalysisProvider analysisProvider;

    public ASMStaticAnalysisProviderExtension()
    {
        super();
    }

    @Override
    public IAnalysisProvider getAnalysisProvider()
    {
        if (null == this.analysisProvider)
        {
            this.analysisProvider = ASMStaticAnalysisProviderCreator.create();
        }
        return this.analysisProvider;
    }
}

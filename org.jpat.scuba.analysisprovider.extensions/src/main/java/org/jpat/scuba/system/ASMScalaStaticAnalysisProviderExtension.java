package org.jpat.scuba.system;

import org.jpat.scuba.analysisprovider.statica.asm.scala.command.system.ASMScalaStaticAnalysisProviderCreator;
import org.jpat.scuba.core.command.system.IAnalysisProviderExtension;
import org.jpat.scuba.core.model.IAnalysisProvider;

public final class ASMScalaStaticAnalysisProviderExtension implements IAnalysisProviderExtension
{
    private IAnalysisProvider analysisProvider;

    public ASMScalaStaticAnalysisProviderExtension()
    {
        super();
    }

    @Override
    public IAnalysisProvider getAnalysisProvider()
    {
        if (null == this.analysisProvider)
        {
            this.analysisProvider = ASMScalaStaticAnalysisProviderCreator.create();
        }
        return this.analysisProvider;
    }
}

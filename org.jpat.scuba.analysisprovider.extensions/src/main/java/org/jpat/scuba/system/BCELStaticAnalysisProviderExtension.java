package org.jpat.scuba.system;

import org.jpat.scuba.analysisprovider.statica.bcel.command.system.BCELStaticAnalysisProviderCreator;
import org.jpat.scuba.core.command.system.IAnalysisProviderExtension;
import org.jpat.scuba.core.model.IAnalysisProvider;

public final class BCELStaticAnalysisProviderExtension implements IAnalysisProviderExtension
{
    private IAnalysisProvider analysisProvider;

    public BCELStaticAnalysisProviderExtension()
    {
        super();
    }

    @Override
    public IAnalysisProvider getAnalysisProvider()
    {
        if (null == this.analysisProvider)
        {
            this.analysisProvider = BCELStaticAnalysisProviderCreator.create();
        }
        return this.analysisProvider;
    }
}

package org.jpat.scuba.system;

import org.jpat.scuba.analysisprovider.dynamic.bcel.command.system.BCELDynamicAnalysisProviderCreater;
import org.jpat.scuba.core.command.system.IAnalysisProviderExtension;
import org.jpat.scuba.core.model.IAnalysisProvider;

public final class BCELDynamicAnalysisProviderExtension implements IAnalysisProviderExtension
{
    private IAnalysisProvider analysisProvider;

    public BCELDynamicAnalysisProviderExtension()
    {
        super();
    }

    @Override
    public IAnalysisProvider getAnalysisProvider()
    {
        if (null == this.analysisProvider)
        {
            this.analysisProvider = BCELDynamicAnalysisProviderCreater.create();
        }
        return this.analysisProvider;
    }
}

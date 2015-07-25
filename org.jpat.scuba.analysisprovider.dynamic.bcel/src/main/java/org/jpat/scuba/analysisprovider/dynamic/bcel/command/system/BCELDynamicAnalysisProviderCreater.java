package org.jpat.scuba.analysisprovider.dynamic.bcel.command.system;

import org.jpat.scuba.analysisprovider.dynamic.bcel.controller.BCELDynamicAnalysisProvider;
import org.jpat.scuba.core.model.IAnalysisProvider;

public final class BCELDynamicAnalysisProviderCreater
{
    private BCELDynamicAnalysisProviderCreater()
    {
        super();
    }

    public static IAnalysisProvider create()
    {
        return new BCELDynamicAnalysisProvider();
    }
}

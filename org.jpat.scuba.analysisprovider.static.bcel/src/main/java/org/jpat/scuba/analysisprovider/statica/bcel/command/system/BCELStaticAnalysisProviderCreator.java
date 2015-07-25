package org.jpat.scuba.analysisprovider.statica.bcel.command.system;

import org.jpat.scuba.analysisprovider.statica.bcel.controller.BCELStaticAnalysisProvider;
import org.jpat.scuba.core.model.IAnalysisProvider;

public final class BCELStaticAnalysisProviderCreator
{
    private BCELStaticAnalysisProviderCreator()
    {
        super();
    }

    public static IAnalysisProvider create()
    {
        return new BCELStaticAnalysisProvider();
    }
}

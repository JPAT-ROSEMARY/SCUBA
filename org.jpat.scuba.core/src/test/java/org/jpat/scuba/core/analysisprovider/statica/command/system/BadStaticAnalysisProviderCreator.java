package org.jpat.scuba.core.analysisprovider.statica.command.system;

import org.jpat.scuba.core.analysisprovider.statica.controller.BadStaticAnalysisProvider;
import org.jpat.scuba.core.model.IAnalysisProvider;

public final class BadStaticAnalysisProviderCreator
{
    private BadStaticAnalysisProviderCreator()
    {
        super();
    }

    public static IAnalysisProvider create()
    {
        return new BadStaticAnalysisProvider();
    }
}

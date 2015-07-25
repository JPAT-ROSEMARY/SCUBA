package org.jpat.scuba.core.analysisprovider.statica.controller;

import org.jpat.scuba.core.controller.StaticAnalysisProvider;
import org.jpat.scuba.core.foundation.BadAnalysis;
import org.jpat.scuba.core.foundation.BadLanguage;

public final class BadStaticAnalysisProvider extends StaticAnalysisProvider
{
    public BadStaticAnalysisProvider()
    {
        super(BadAnalysis.INSTANCE, BadLanguage.INSTANCE);
    }
}

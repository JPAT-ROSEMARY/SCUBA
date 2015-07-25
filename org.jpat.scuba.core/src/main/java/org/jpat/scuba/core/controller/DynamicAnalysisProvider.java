package org.jpat.scuba.core.controller;

import org.jpat.scuba.core.foundation.Analysis;
import org.jpat.scuba.core.foundation.Language;

public abstract class DynamicAnalysisProvider extends AnalysisProvider
{
    protected DynamicAnalysisProvider(final Analysis analysis, final Language language)
    {
        super(analysis, language);
    }

    @Override
    public boolean isPostAnalysisRequired()
    {
        return true;
    }
}

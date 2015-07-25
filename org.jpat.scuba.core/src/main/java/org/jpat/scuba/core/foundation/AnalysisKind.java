package org.jpat.scuba.core.foundation;

import org.jpat.scuba.common.util.ISelfIdentifier;

public abstract class AnalysisKind implements ISelfIdentifier
{
    public abstract String getDetail();

    @Override
    public String toString()
    {
        return getTechnicalValue();
    }
}

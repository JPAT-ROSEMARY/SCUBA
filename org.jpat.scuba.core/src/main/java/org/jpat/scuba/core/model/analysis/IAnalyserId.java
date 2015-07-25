package org.jpat.scuba.core.model.analysis;

import org.jpat.scuba.common.util.ISelfIdentifier;

public interface IAnalyserId extends ISelfIdentifier
{
    Category getCategory();

    String getDetail();
}

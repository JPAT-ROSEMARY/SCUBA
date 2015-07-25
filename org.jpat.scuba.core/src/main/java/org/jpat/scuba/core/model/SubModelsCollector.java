package org.jpat.scuba.core.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class SubModelsCollector
{
    protected static List<AliasedScuba> getChildren(final ArrayList<AliasedScuba> children)
    {
        if (null == children)
        {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(children);
    }

    protected static Iterator<AliasedScuba> getChildrenIterator(final ArrayList<AliasedScuba> children)
    {
        if (null == children)
        {
            return AliasedScuba.EMPTY_ALIASED_SCUBA_ITERATOR;
        }
        return children.iterator();
    }
}

package org.jpat.scuba.core.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AliasedScubaContainer extends AliasedScuba
{
    private ArrayList<AliasedScuba> children;

    protected AliasedScubaContainer(final AliasedScuba parent)
    {
        super(parent);
    }

    protected AliasedScubaContainer()
    {
        super();
    }

    @Override
    public void addChild(final AliasedScuba child)
    {
        assert null != child : "Parameter 'child' of method 'addChild' must not be null";
        assert !hasChild(child) : "'child' already added: " + child;
        if (null == this.children)
        {
            this.children = new ArrayList<>();
        }
        this.children.add(child);
    }

    @Override
    protected List<AliasedScuba> getChildren()
    {
        return SubModelsCollector.getChildren(this.children);
    }

    @Override
    protected final Iterator<AliasedScuba> getChildrenIterator()
    {
        return SubModelsCollector.getChildrenIterator(this.children);
    }
}

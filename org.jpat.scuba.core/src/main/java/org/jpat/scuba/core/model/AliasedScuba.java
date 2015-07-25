package org.jpat.scuba.core.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.jpat.scuba.common.util.EmptyIterator;

public abstract class AliasedScuba extends Scuba
{
    protected static final EmptyIterator<AliasedScuba> EMPTY_ALIASED_SCUBA_ITERATOR = new EmptyIterator<AliasedScuba>();

    protected final AliasedScuba parent;

    private final String emptyDetailValue = EMPTY;

    protected AliasedScuba(final AliasedScuba parent)
    {
        assert null != parent : "Parameter 'parent' of method 'AliasedScuba' must not be null";

        this.parent = parent;
    }

    protected AliasedScuba()
    {
        this.parent = null;
    }

    public abstract String getAlias();

    @Override
    public <T extends IExtension> T getExtension(final Class<T> extensionClass)
    {
        if (null != this.parent)
        {
            return this.parent.getExtension(extensionClass);
        }
        assert false : "No parent";

        return null;
    }

    protected final boolean hasChild(final AliasedScuba child)
    {
        assert null != child : "Parameter 'child' of method 'hasChild' must not be null"; //$NON-NLS-1$

        if (hasChildren())
        {
            return getChildren().contains(child);
        }
        return false;
    }

    protected final boolean hasChildren()
    {
        return !getChildren().isEmpty();
    }

    public void addChild(final AliasedScuba scuba)
    {
        assert false : "'addChild' not supported: " + getClass().getName();
    }

    public final <T> T getChild(final Class<T> clazz)
    {
        assert null != clazz : "Parameter 'clazz' of method 'getChild' must not be null";

        T found = null;
        final Iterator<T> iterator = getChildrenIterator(clazz);
        while (iterator.hasNext())
        {
            assert null == found : "More than one child of classs '" + clazz.getSimpleName() + "' found in: " + this;

            found = iterator.next();
        }
        return found;
    }

    protected Iterator<AliasedScuba> getChildrenIterator()
    {
        if (!hasChildren())
        {
            return EMPTY_ALIASED_SCUBA_ITERATOR;
        }
        return getChildren().iterator();
    }

    public final <T> Iterator<T> getChildrenIterator(final Class<T> clazz)
    {
        return getChildrenIterator(clazz, false);
    }

    public final <T> Iterator<T> getChildrenIterator(final Class<T> clazz, final boolean exactClass)
    {
        assert null != clazz : "Parameter 'clazz' of method 'getChildrenIterator' must not be null";

        return new Iterator<T>()
        {
            private final Iterator<? extends AliasedScuba> childIterator = getChildrenIterator();
            private T next;

            @SuppressWarnings("unchecked")
            @Override
            public boolean hasNext()
            {
                while (this.childIterator.hasNext() && null == this.next)
                {
                    final AliasedScuba nextChild = this.childIterator.next();
                    if (exactClass ? clazz.equals(nextChild.getClass()) : clazz.isAssignableFrom(nextChild.getClass()))
                    {
                        this.next = (T) nextChild;
                    }
                }
                return null != this.next;
            }

            @Override
            public T next()
            {
                assert null != this.next : "Field 'next' in method 'next' must not be null";

                final T next = this.next;
                this.next = null;
                return next;
            }

            @Override
            public void remove()
            {
                assert false : "'Remove' feature is not supported";
            }
        };
    }

    @SuppressWarnings("static-method")
    protected List<AliasedScuba> getChildren()
    {
        return Collections.emptyList();
    }

    public final <T> List<T> getChildren(final Class<T> clazz)
    {
        return getChildren(clazz, false);
    }

    @SuppressWarnings("unchecked")
    public final <T> List<T> getChildren(final Class<T> clazz, final boolean exactClass)
    {
        assert null != clazz : "Parameter 'clazz' of method 'getChildren' must not be null";

        final List<T> filtered = new ArrayList<T>();
        getRealChildren().forEach(nextChild -> {
            if (exactClass ? clazz.equals(nextChild.getClass()) : clazz.isAssignableFrom(nextChild.getClass()))
            {
                filtered.add((T) nextChild);
            }
        });
        return filtered;
    }

    public List<AliasedScuba> getRealChildren()
    {
        return getChildren();
    }

    public String getDetail()
    {
        /** Children may override */
        return this.emptyDetailValue;
    }
}

package org.jpat.scuba.common.util;

import java.util.Iterator;

public class EmptyIterator<T> implements Iterator<T>
{
    public EmptyIterator()
    {
        super();
    }

    @Override
    public boolean hasNext()
    {
        return false;
    }

    @Override
    public T next()
    {
        assert false : "No more objects available";

        return null;
    }

    @Override
    public void remove()
    {
        assert false : "Remove not supported - no more ovjects available";
    }
}

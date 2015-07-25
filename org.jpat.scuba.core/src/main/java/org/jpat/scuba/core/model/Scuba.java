package org.jpat.scuba.core.model;

public abstract class Scuba
{
    public static final String EMPTY = "";

    protected Scuba()
    {
        super();
    }

    public abstract <T extends IExtension> T getExtension(Class<T> extensionClass);
}

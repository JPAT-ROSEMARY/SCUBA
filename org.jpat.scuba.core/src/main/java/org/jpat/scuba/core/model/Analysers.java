package org.jpat.scuba.core.model;

public final class Analysers extends AliasedScubaContainer
{
    public Analysers(final AliasedScuba parent)
    {
        super(parent);
    }

    @Override
    public String getDetail()
    {
        return "Contains pre-configured Analysers";
    }

    @Override
    public String toString()
    {
        return this.getDetail();
    }

    @Override
    public String getAlias()
    {
        return this.getClass().getSimpleName();
    }
}

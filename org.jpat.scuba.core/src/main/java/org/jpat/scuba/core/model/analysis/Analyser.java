package org.jpat.scuba.core.model.analysis;

import org.jpat.scuba.core.model.AliasedScuba;
import org.jpat.scuba.core.model.AliasedScubaContainer;

public final class Analyser extends AliasedScubaContainer
{
    private final IAnalyserId id;

    public Analyser(final AliasedScuba parent, final IAnalyserId id)
    {
        super(parent);

        assert null != id : "Parameter 'id' of 'Analyser''s ctor must not be null";

        this.id = id;
    }

    @Override
    public String getAlias()
    {
        return this.id.getNaturalValue();
    }

    public IAnalyserId getId()
    {
        return this.id;
    }

    public void start()
    {
        //TODO
    }
}

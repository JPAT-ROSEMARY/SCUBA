package org.jpat.scuba.core.model.benchmark;

import org.jpat.scuba.core.model.AliasedScuba;
import org.jpat.scuba.core.model.AliasedScubaContainer;

public final class BytecodeArtifactsAnalysedAll extends AliasedScubaContainer
{
    public BytecodeArtifactsAnalysedAll(final AliasedScuba parent)
    {
        super(parent);
    }

    @Override
    public String getAlias()
    {
        return this.getClass().getSimpleName();
    }
}

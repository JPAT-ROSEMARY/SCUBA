package org.jpat.scuba.core.model.analysis;

import org.jpat.scuba.core.model.AliasedScuba;
import org.jpat.scuba.core.model.AliasedScubaContainer;

public abstract class ScubaAnalysisOutcome extends AliasedScubaContainer
{
    protected ScubaAnalysisOutcome(final AliasedScuba parent)
    {
        super(parent);
    }

    public ScubaAnalysisOutcome()
    {
        super();
    }
}

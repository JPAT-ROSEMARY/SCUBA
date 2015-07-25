package org.jpat.scuba.core.controller.analysis;

import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.analysis.CoreAnalyserId;

public class ClassLevelDependencyAnalysisAdapter extends AnalysisAdapter
{
    public ClassLevelDependencyAnalysisAdapter(final FANChallenge fanChallenge)
    {
        super(fanChallenge, CoreAnalyserId.CLASS_LEVEL_DEPENDENCY_ANALYSER);
    }

    @Override
    protected void internalRun()
    {
        assert false : "implementation has not been provided yet";
        /** Future work*/
    }
}

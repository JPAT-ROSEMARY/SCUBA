package org.jpat.scuba.core.controller.analysis;

import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.analysis.IAnalyserId;

/**
 * 
 * InstrumentationAdapter extends StatsAnalysisAdapter
 * because currently we are supporting the same feature of but at runtime and for learning purposes 
 * we need the stats in both cases and that for reuse I chose this structure.
 * However, for future work this could be a special instrument case and then we make another hierarchy that may extend AnalysisAdapter directly. 
 */
public abstract class InstrumentationAdapter extends StatsAnalysisAdapter
{
    protected InstrumentationAdapter(final FANChallenge fanChallenge, final IAnalyserId analyserId, final AbstractTargetedAnalysis targetedAnalysis)
    {
        super(fanChallenge, analyserId, targetedAnalysis);
    }
}

package org.jpat.scuba.core.controller.analysis;

import org.jpat.scuba.core.model.Analysers;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IClassParser;
import org.jpat.scuba.core.model.analysis.Analyser;
import org.jpat.scuba.core.model.analysis.IAnalyserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AnalysisAdapter implements Runnable
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisAdapter.class);

    private final FANChallenge fanchallenge;
    private final Analyser analyser;
    protected IClassParser classParser;

    protected AnalysisAdapter(final FANChallenge fanChallenge, final IAnalyserId analyserId)
    {
        assert null != fanChallenge : "Parameter 'fanChallenge' of 'AnalysisAdapter''s ctor must not be null";
        assert null != analyserId : "Parameter 'analyserId' of 'AnalysisAdapter''s ctor must not be null";

        this.fanchallenge = fanChallenge;
        final Analysers analysers = this.fanchallenge.getAnalysers();
        Analyser analyser = null;
        for (final Analyser nextAnalyser : analysers.getChildren(Analyser.class))
        {
            if (nextAnalyser.getId() == analyserId)
            {
                analyser = nextAnalyser;
                break;
            }
        }
        assert null != analyser : "'analyser' of method 'AnalysisAdapter' must not be null";

        this.analyser = analyser;
    }

    protected abstract void internalRun();

    @Override
    public void run()
    {
        this.analyser.start();
        LOGGER.info("analysis in progress...");
        internalRun();
    }

    protected final Analyser getAnalyser()
    {
        return this.analyser;
    }

    protected final FANChallenge getFANChallenge()
    {
        return this.fanchallenge;
    }

    @Override
    public String toString()
    {
        return this.analyser.getAlias();
    }
}

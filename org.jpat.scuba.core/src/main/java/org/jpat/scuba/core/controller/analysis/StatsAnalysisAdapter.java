package org.jpat.scuba.core.controller.analysis;

import java.util.List;

import org.jpat.scuba.common.util.IPathResolver;
import org.jpat.scuba.core.controller.ScubaController;
import org.jpat.scuba.core.controller.StatsProvider;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.analysis.IAnalyserId;
import org.jpat.scuba.core.model.benchmark.BytecodeArtifact;
import org.jpat.scuba.foundation.report.Counter;
import org.jpat.scuba.foundation.report.RuntimeStopwatch;

public abstract class StatsAnalysisAdapter extends AnalysisAdapter
{
    protected final AbstractTargetedAnalysis targetedAnalysis;

    protected StatsAnalysisAdapter(final FANChallenge fanChallenge, final IAnalyserId analyserId, final AbstractTargetedAnalysis targetedAnalysis)
    {
        super(fanChallenge, analyserId);

        assert null != targetedAnalysis : "Parameter 'targetedAnalysis' of 'StatsAnalysisAdapter''s ctor must not be null";

        this.targetedAnalysis = targetedAnalysis;
    }

    @Override
    public final void run()
    {
        super.run();

        final List<BytecodeArtifact> binaryArtifacts = getFANChallenge().getBytecodeArtifacts();

        assert null != binaryArtifacts : "'binaryArtifacts' in method 'run' must not be null";

        final IPathResolver directoryResolver = getFANChallenge().getBytecodeContainer().getPathResolver();

        Counter.INSTANCE.init(getAnalyser().getId().getNaturalValue());
        RuntimeStopwatch.INSTANCE.init(getAnalyser().getId().getDetail());

        binaryArtifacts.forEach(next -> {
            getFANChallenge().getExtension(StatsProvider.class).createNewStats4SingleType();

            if (next.isClassFile()
                    && (!getFANChallenge().getExtension(ScubaController.class).isExternalExcluded() || !((BytecodeArtifact) next).isExternal()))
            {
                Counter.INSTANCE.incrementAnalysedTypesCounter();

                this.classParser.parse(next, directoryResolver);
            }
            else
            {
                getFANChallenge().addBytecodeArtificatAnalysed(next.getClassQualifiedName(), next.getBytesOriginal(), next.absolutePath(),
                        directoryResolver, next.isClassFile(), false);
            }
        });
    }
}


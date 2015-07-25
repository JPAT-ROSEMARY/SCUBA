package org.jpat.scuba.core.controller;

import java.util.List;

import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IStatsProvider;
import org.jpat.scuba.core.model.analysis.config.ArtifactTargetedConfigurator;
import org.jpat.scuba.core.model.benchmark.BytecodeArtifactAnalysed;
import org.jpat.scuba.core.model.benchmark.BytecodeArtifactsAnalysedAll;
import org.jpat.scuba.core.model.stats.BytecodeStats4All;
import org.jpat.scuba.core.model.stats.BytecodeStats;
import org.jpat.scuba.core.model.stats.BytecodeTargetedStats;

public final class StatsProvider implements IStatsProvider
{
    private final BytecodeStats4All bytecodeStats4All;
    private ArtifactTargetedConfigurator artifactTargetedChecker;
    private BytecodeStats nextstats4SingleType;

    public StatsProvider(final FANChallenge benchmark)
    {
        assert null != benchmark : "Parameter 'benchmark' of 'StatsProvider''s ctor must not be null";

        this.bytecodeStats4All = new BytecodeStats4All();
        final BytecodeArtifactsAnalysedAll bytecodeArtifactsAnalysedAll = new BytecodeArtifactsAnalysedAll(benchmark);
        bytecodeArtifactsAnalysedAll.addChild(this.bytecodeStats4All);
    }

    @Override
    public BytecodeStats4All getStats()
    {
        return this.bytecodeStats4All;
    }

    public void configureStatsExtension(final ArtifactTargetedConfigurator artifactTargetedChecker)
    {
        assert null != artifactTargetedChecker : "Parameter 'artifactTargetedChecker' of method 'configureStatsExtension' must not be null";

        this.artifactTargetedChecker = artifactTargetedChecker;
    }

    @Override
    public void record4SingleType(final BytecodeArtifactAnalysed bytecodeArtifactAnalysed)
    {
        this.bytecodeStats4All.addChild(bytecodeArtifactAnalysed); //TODO should has as children the bytecodeStats not only the bytecode file

        assert null != this.nextstats4SingleType : "Be careful! An attempt to duplicate or instantiates stats 4 one type without having configured an stats4SingleType object";

        this.bytecodeStats4All.record4All(this.nextstats4SingleType);
        this.nextstats4SingleType = null;
    }

    public void createNewStats4SingleType()
    {
        final BytecodeTargetedStats statsTargeted = this.artifactTargetedChecker.createStats4TargetedArtifact(); //TODO stats 4 all types loses the context of this
        this.nextstats4SingleType = new BytecodeStats(statsTargeted);
        this.nextstats4SingleType.incrementAnalysedTypesCounter();
    }

    @Override
    public BytecodeStats getBytecodeStats()
    {
        return this.nextstats4SingleType;
    }

    public List<BytecodeArtifactAnalysed> artifactsAnalysed()
    {
        final List<BytecodeArtifactAnalysed> scubaOutput4SingleTList = this.bytecodeStats4All.getChildren(BytecodeArtifactAnalysed.class);

        assert null != scubaOutput4SingleTList : "'scubaOutput4SingleTList' of method 'artifacts' must not be null";

        return scubaOutput4SingleTList;
    }
}

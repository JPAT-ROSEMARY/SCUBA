package org.jpat.scuba.core.model.stats;

import org.jpat.scuba.core.model.analysis.config.ArtifactTargetedConfigurator;

public abstract class BytecodeTargetedStats extends ScubaStats
{
    private volatile Integer targetedArtifactStats;
    private final ArtifactTargetedConfigurator artifactTargetedChecker;

    protected BytecodeTargetedStats(final ArtifactTargetedConfigurator artifactTargetedChecker)
    {
        super();

        assert null != artifactTargetedChecker : "Parameter 'artifactTargetedChecker' of 'BytecodeStatsTargeted''s ctor must not be null";

        this.artifactTargetedChecker = artifactTargetedChecker;
        this.targetedArtifactStats = Integer.valueOf(0);
    }

    public final void incrementTargetedArtifact(final int opcode)
    {
        if (this.artifactTargetedChecker.check(opcode))
        {
            this.targetedArtifactStats = Operations4Stats.incrementStatsEntry(this.targetedArtifactStats);
        }
    }

    public Integer targetedArtifactCount()
    {
        return this.targetedArtifactStats;
    }
}

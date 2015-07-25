package org.jpat.scuba.core.model.analysis.config;

import org.jpat.scuba.core.model.stats.BytecodeTargetedStats;

public abstract class ArtifactTargetedConfigurator
{
    private final int opcodeTargeted;

    protected ArtifactTargetedConfigurator(final int opcodeTargeted)
    {
        this.opcodeTargeted = opcodeTargeted;
    }

    public abstract BytecodeTargetedStats createStats4TargetedArtifact();

    public final boolean check(final int opcode)
    {
        return this.opcodeTargeted == opcode;
    }
}

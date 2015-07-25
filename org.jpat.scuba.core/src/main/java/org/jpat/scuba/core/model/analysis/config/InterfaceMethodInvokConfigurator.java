package org.jpat.scuba.core.model.analysis.config;

import org.jpat.scuba.core.model.stats.InterfaceMethodInvocationStats;
import org.jpat.scuba.core.model.stats.BytecodeTargetedStats;

public abstract class InterfaceMethodInvokConfigurator extends ArtifactTargetedConfigurator
{
    protected InterfaceMethodInvokConfigurator(final int opcodeTargeted)
    {
        super(opcodeTargeted);
    }

    @Override
    public BytecodeTargetedStats createStats4TargetedArtifact()
    {
        return new InterfaceMethodInvocationStats(this);
    }

    @Override
    public final String toString()
    {
        return "Configure to target INVOKEINTERFACE";
    }
}

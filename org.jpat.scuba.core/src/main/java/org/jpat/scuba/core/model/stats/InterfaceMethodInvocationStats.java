package org.jpat.scuba.core.model.stats;

import org.jpat.scuba.core.model.analysis.config.ArtifactTargetedConfigurator;

public final class InterfaceMethodInvocationStats extends BytecodeTargetedStats
{
    public InterfaceMethodInvocationStats(final ArtifactTargetedConfigurator artifactTargetedChecker)
    {
        super(artifactTargetedChecker);
    }

    @Override
    public String getAlias()
    {
        return "Stats on interface method invocation";
    }
}

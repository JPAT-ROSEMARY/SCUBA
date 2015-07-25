package org.jpat.scuba.core.model;

import org.jpat.scuba.core.model.benchmark.BytecodeArtifactAnalysed;
import org.jpat.scuba.core.model.stats.BytecodeStats4All;
import org.jpat.scuba.core.model.stats.BytecodeStats;

public interface IStatsProvider extends IExtension
{
    BytecodeStats4All getStats();

    void record4SingleType(final BytecodeArtifactAnalysed bytecodeArtifactAnalysed);

    BytecodeStats getBytecodeStats();
}

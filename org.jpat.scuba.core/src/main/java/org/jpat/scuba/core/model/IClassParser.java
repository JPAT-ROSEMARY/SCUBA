package org.jpat.scuba.core.model;

import org.jpat.scuba.common.util.IPathResolver;
import org.jpat.scuba.core.model.benchmark.BytecodeArtifact;

public interface IClassParser
{
    public void parse(final BytecodeArtifact nextBytecodeArtifact, final IPathResolver directoryResolver);
}

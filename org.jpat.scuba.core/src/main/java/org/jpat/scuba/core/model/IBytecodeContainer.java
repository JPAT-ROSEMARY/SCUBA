package org.jpat.scuba.core.model;

import org.jpat.scuba.common.util.IPathResolver;

import de.schlichtherle.truezip.file.TFile;

public interface IBytecodeContainer
{
    void addBytecode4(final String typeName, final TFile bytecodeFile);

    IPathResolver getPathResolver();
}

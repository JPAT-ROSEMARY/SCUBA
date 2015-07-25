package org.jpat.scuba.core.model;

import org.jpat.scuba.core.model.benchmark.BenchmarkBytecodeContainer;

import de.schlichtherle.truezip.file.TFile;

public final class Artifacts extends AliasedScubaContainer
{
    private final String benchmarkExampleName;
    private final TFile benchmarkBaseDirectory;
    private final TFile benchmarkExampleBytecodePlace;

    public Artifacts(final FANChallenge parent, final String benchmarkExample, final TFile bytecodeArchiveFile)
    {
        super(parent);

        assert null != benchmarkExample && !benchmarkExample.isEmpty() : "Parameter 'benchmarkExampleName' of 'Artifacts''s ctor must not be empty"; //$NON-NLS-1$
        assert null != bytecodeArchiveFile : "Parameter 'bytecodeArchiveFile' of 'Artifacts''s ctor must not be null";

        this.benchmarkExampleName = benchmarkExample;
        this.benchmarkExampleBytecodePlace = bytecodeArchiveFile;
        this.benchmarkBaseDirectory = bytecodeArchiveFile.getParentFile();
        addChild(new BenchmarkBytecodeContainer(parent, bytecodeArchiveFile, this.benchmarkExampleName));
    }

    @Override
    public String getAlias()
    {
        return this.benchmarkExampleName;
    }

    public TFile getBenchmarkBaseDirectory()
    {
        return this.benchmarkBaseDirectory;
    }

    public TFile getBenchmarkExampleBytecodePlace()
    {
        return this.benchmarkExampleBytecodePlace;
    }

    @Override
    public String getDetail()
    {
        return "Contains input bytecode means for SCUBA";
    }

    @Override
    public String toString()
    {
        return this.getDetail();
    }
}

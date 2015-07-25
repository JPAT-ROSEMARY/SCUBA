package org.jpat.scuba.core.model.benchmark;

import org.jpat.scuba.common.util.Category;
import org.jpat.scuba.common.util.FileType;
import org.jpat.scuba.common.util.IPathResolver;
import org.jpat.scuba.core.controller.benchmark.BytecodeContainerPathResolver;
import org.jpat.scuba.core.model.AliasedScubaContainer;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IBytecodeContainer;

import de.schlichtherle.truezip.file.TFile;

public final class BenchmarkBytecodeContainer extends AliasedScubaContainer implements IBytecodeContainer
{
    private final TFile bytecodeArtifact;
    private final String benchmarkExampleName;
    private final FileType type;
    private final IPathResolver pathResolver;

    public BenchmarkBytecodeContainer(final FANChallenge parent, final TFile benchmarkExampleFile, final String benchmarkExampleName)
    {
        super(parent);

        assert null != benchmarkExampleFile : "Parameter 'benchmarkExampleFile' of 'BenchmarkBytecodeContainer''s ctor must not be null";

        this.bytecodeArtifact = benchmarkExampleFile;
        this.benchmarkExampleName = benchmarkExampleName;

        this.type = FileType.resolve(this.bytecodeArtifact);

        assert null != this.type : "'this.type' in 'BenchmarkBytecodeContainer''s ctor must not be null";
        assert Category.ARCHIVE == this.type.getCategory() : "File '" + this.bytecodeArtifact + "' must be Archive";

        this.pathResolver = new BytecodeContainerPathResolver(this.benchmarkExampleName, true);
    }

    @Override
    public String getAlias()
    {
        return this.benchmarkExampleName;
    }

    public String getBytecodeArtifactPath()
    {
        return this.bytecodeArtifact.getPath();
    }

    public String getBenchmarkExampleName()
    {
        return this.benchmarkExampleName;
    }

    public FileType getType()
    {
        return this.type;
    }

    @Override
    public IPathResolver getPathResolver()
    {
        return this.pathResolver;
    }

    @Override
    public void addBytecode4(final String artifactName, final TFile bytecodeFile)
    {
        assert null != artifactName && !artifactName.isEmpty() : "Parameter 'artifactName' of method 'addBytecode4' must not be empty";
        assert null != bytecodeFile : "Parameter 'bytecodeFile' of 'addBytecode4''s ctor must not be null";

        final BytecodeArtifact bytecodeArtifact = new BytecodeArtifact(this, artifactName, bytecodeFile, this.pathResolver);
        this.addChild(bytecodeArtifact);
    }

    @Override
    public String toString()
    {
        return this.benchmarkExampleName;
    }
}

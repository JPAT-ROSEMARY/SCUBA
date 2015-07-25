package org.jpat.scuba.core.model.benchmark;

import org.jpat.scuba.common.util.FileUtil;
import org.jpat.scuba.common.util.IPathResolver;
import org.jpat.scuba.core.model.AliasedScuba;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IStatsProvider;
import org.jpat.scuba.core.model.stats.BytecodeStats;
import org.jpat.scuba.foundation.io.SimpleStreamWriter;

import de.schlichtherle.truezip.file.TFile;

public final class BytecodeArtifactAnalysed extends AliasedScuba
{
    private final FANChallenge benchmark;
    private final String typeInternalName;
    private final byte[] bytecode;
    private final TFile file;
    private final IPathResolver pathResolver;
    private final String artifactRelativePath;
    private final boolean isClassFile;
    private final boolean isInterface;
    private final BytecodeStats stats4SingleType;

    public BytecodeArtifactAnalysed(final FANChallenge benchmark, final String typeInternalName, final byte[] bytes,
            final BytecodeStats stats4SingleType, final String fileAbsolutePath, final IPathResolver directoryResolver, final boolean isClassFile,
            final boolean isInterface)
    {
        super(benchmark);

        assert null != typeInternalName && !typeInternalName.isEmpty() : "Parameter 'typeInternalName' of 'BytecodeArtifactAnalysed''s ctor must not be empty";
        assert null != bytes && 0 < bytes.length : "Parameter 'bytes' of 'ScubaOutput4SingleType''s ctor must not be empty";
        assert null != stats4SingleType : "Parameter 'stats4SingleType' of 'BytecodeArtifactAnalysed''s ctor must not be null";
        assert null != fileAbsolutePath && !fileAbsolutePath.isEmpty() : "Parameter 'fileAbsolutePath' of 'BytecodeArtifactAnalysed''s ctor must not be empty";
        assert null != directoryResolver : "Parameter 'directoryResolver' of 'ScubaOutput4SingleType''s ctor must not be null";

        this.benchmark = benchmark;

        this.isClassFile = isClassFile;
        if (this.isClassFile)
        {
            this.typeInternalName = FileUtil.postFixTypeInternalNameWithClassExtension(typeInternalName);
        }
        else
        {
            this.typeInternalName = typeInternalName;
        }

        this.isInterface = isInterface;

        assert this.isClassFile | !this.isInterface : "an interface should be a valid bytecode type";

        this.bytecode = bytes;
        this.stats4SingleType = stats4SingleType;
        this.file = new TFile(fileAbsolutePath);
        assert this.file.isAbsolute() : "'file' must be absolute";
        this.pathResolver = directoryResolver;
        this.artifactRelativePath = directoryResolver.relativise(fileAbsolutePath);
        this.benchmark.getExtension(IStatsProvider.class).record4SingleType(this);
        if (this.isClassFile)
        {
            //TODO there should be a configuration control on whether to persist in case of Static Analysis ! 
            // this.benchmark.getPersistenceProvider(this.directoryNameResolver, this.typeInternalName, this.bytecode).persist();
            persist();
        }
    }

    public BytecodeStats getBytecodeStats()
    {
        return this.stats4SingleType;
    }

    public TFile getBytecodeArtifact()
    {
        return this.file;
    }

    public String getArtifactRelativePath()
    {
        return this.artifactRelativePath;
    }

    public byte[] getByteCode()
    {
        return this.bytecode;
    }

    public String getTypeInternalName()
    {
        return this.typeInternalName;
    }

    public boolean isitClassFile()
    {
        return this.isClassFile;
    }

    @Override
    public String getAlias()
    {
        return this.file.getName();
    }

    private TFile persist()
    {
        return SimpleStreamWriter.write(this.artifactRelativePath, this.bytecode, this.pathResolver);
    }

    @Override
    public String toString()
    {
        return this.typeInternalName;
    }
}

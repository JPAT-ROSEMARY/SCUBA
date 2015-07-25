package org.jpat.scuba.core.model.benchmark;

import org.jpat.scuba.common.util.FileUtil;
import org.jpat.scuba.common.util.IPathResolver;
import org.jpat.scuba.core.model.AliasedScuba;

import de.schlichtherle.truezip.file.TFile;

public final class BytecodeArtifact extends AliasedScuba
{
    private final String artifactName;
    private final TFile file;
    private final String classSimpleName;
    private final String classQuallifiedName;
    private final String absolutePath;
    private final boolean external;
    private final boolean isClassFile;
    private final byte[] bytesOriginal;
    private final String artifactRelativePath;

    public BytecodeArtifact(final BenchmarkBytecodeContainer parent, final String artifactName, final TFile file,
            final IPathResolver directoryResolver)
    {
        super(parent);

        assert null != artifactName && !artifactName.isEmpty() : "Parameter 'qualifiedName' of method 'BytecodeArtifact' must not be empty";
        assert null != file : "Parameter 'file' of 'BytecodeArtifact''s ctor must not be null";
        assert null != directoryResolver : "Parameter 'directoryResolver' of method 'BytecodeArtifact' must not be null";

        this.artifactName = artifactName;
        this.file = file;
        this.classSimpleName = this.file.getName();
        this.classQuallifiedName = FileUtil.removeDotExtension(this.artifactName);
        this.absolutePath = file.getAbsolutePath();
        this.external = ArtifactIsExternalChecker.checkIsExternal(this.artifactName, parent);
        this.isClassFile = FileUtil.isClassFile(this.file);
        this.bytesOriginal = FileUtil.readFileToByteArray(this.file);

        assert null != this.bytesOriginal : "'bytesOriginal' in 'BytecodeArtifact''s ctor must not be null";

        this.artifactRelativePath = directoryResolver.relativise(this.absolutePath);
    }

    @Override
    public String getAlias()
    {
        return this.file.getName();
    }

    public TFile getArtifactSystemFile()
    {
        return this.file;
    }

    public String absolutePath()
    {
        return this.absolutePath;
    }

    public String getClassSimpleName()
    {
        return this.classSimpleName;
    }

    public String getClassQualifiedName()
    {
        return this.classQuallifiedName;
    }

    public boolean isExternal()
    {
        return this.external;
    }

    public boolean isClassFile()
    {
        return this.isClassFile;
    }

    public byte[] getBytesOriginal()
    {
        return this.bytesOriginal.clone();
    }

    public String getArtifactRelativePath()
    {
        return this.artifactRelativePath;
    }

    @Override
    public String toString()
    {
        return this.artifactName;
    }
}

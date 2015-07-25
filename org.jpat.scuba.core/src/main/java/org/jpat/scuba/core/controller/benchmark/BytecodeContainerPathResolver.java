package org.jpat.scuba.core.controller.benchmark;

import org.jpat.scuba.common.util.FileUtil;
import org.jpat.scuba.common.util.IPathResolver;
import org.jpat.scuba.foundation.io.AbstractPathResolver;
import org.jpat.scuba.foundation.io.TempDirectory;

import de.schlichtherle.truezip.file.TFile;

public final class BytecodeContainerPathResolver implements IPathResolver
{
    private final AbstractPathResolver tempPathResolver;
    private final TFile absolutePath;
    private final String relativePath;

    public BytecodeContainerPathResolver(final String base, final boolean clean)
    {
        assert null != base && !base.isEmpty() : "Parameter 'base' of 'BytecodeContainerPathResolver''s ctor must not be empty";

        this.tempPathResolver = new TempDirectory(clean);
        this.absolutePath = new TFile(this.tempPathResolver.resolve(base));
        FileUtil.prepareCleanAbsPathDirectory(this.absolutePath.getPath());
        this.relativePath = FileUtil.gluePathAndParentWithSystemFileSeparator(this.tempPathResolver.getRelativePath(), base);
    }

    @Override
    public String resolve(final String path)
    {
        assert null != path && !path.isEmpty() : "Parameter 'path' of method 'resolve' must not be empty";

        return FileUtil.composeAbsPathFromRelativePath(path, this.relativePath);
    }

    @Override
    public String relativise(final String absPath)
    {
        assert null != absPath && !absPath.isEmpty() : "Parameter 'absPath' of method 'relativise' must not be empty";

        return FileUtil.relativise(absPath, this.absolutePath.getPath());
    }
}

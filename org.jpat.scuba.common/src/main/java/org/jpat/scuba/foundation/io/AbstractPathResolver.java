package org.jpat.scuba.foundation.io;

import org.jpat.scuba.common.util.FileUtil;
import org.jpat.scuba.common.util.IPathResolver;

import de.schlichtherle.truezip.file.TFile;

public abstract class AbstractPathResolver implements IPathResolver
{
    private static final String SCUBA = ".SCUBA";

    protected final String scuba = SCUBA;

    protected final String relativePath = composeRelativePath();

    protected final String absPath = composeAbsPath();

    public AbstractPathResolver(final boolean cleanUp)
    {
        if (cleanUp)
        {
            cleanUp();
        }
    }

    protected abstract String specificDirName();

    @Override
    public String resolve(final String path)
    {
        assert null != path && !path.isEmpty() : "Parameter 'path' of method 'resolve' must not be empty";

        String relativePath = path;
        if (new TFile(path).isAbsolute())
        {
            relativePath = FileUtil.relativise(path, this.absPath);
        }
        return FileUtil.composeAbsPathFromRelativePath(relativePath, this.relativePath);
    }

    @Override
    public String relativise(final String absPath)
    {
        assert null != absPath : "Parameter 'absPath' of method 'relativise' must not be null";
        assert !absPath.isEmpty() : "Parameter 'absPath' of method 'relativise' must not be empty";

        return FileUtil.relativise(absPath, this.absPath);
    }

    protected String composeRelativePath()
    {
        return FileUtil.composeSystemCompatiblePath(this.scuba, this.specificDirName());
    }

    protected final void cleanUp()
    {
        FileUtil.prepareCleanAbsPathDirectory(this.absPath);
    }

    protected String composeAbsPath()
    {
        return FileUtil.composePathInUserHomeDirectory(this.relativePath);
    }

    public final String getRelativePath()
    {
        return this.relativePath;
    }
}

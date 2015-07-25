package org.jpat.scuba.foundation.io;

import org.jpat.scuba.common.util.FileUtil;

import de.schlichtherle.truezip.file.TFile;

public final class JarFilePathResolver extends AbstractPathResolver
{
    private static final String DIR_FOR_INSTRUMENT = "instrumented";
    private static final String POST_FIX_INSTRUMENTED = "_" + DIR_FOR_INSTRUMENT;

    public JarFilePathResolver(final boolean cleanUp)
    {
        super(cleanUp);
    }

    @Override
    public String resolve(final String relaOrAbsPath)
    {
        assert null != relaOrAbsPath && !relaOrAbsPath.isEmpty() : "Parameter 'relaOrAbsPath' of method 'resolve' must not be empty";

        final TFile absFile = new TFile(FileUtil.convertRelativeToAbs(relaOrAbsPath));
        final String postFixedJarFileName = FileUtil.postFixFileName(absFile, POST_FIX_INSTRUMENTED);
        final StringBuilder strBldr = new StringBuilder();
        strBldr.append(super.absPath);
        strBldr.append(postFixedJarFileName);
        strBldr.trimToSize();
        return strBldr.toString();
    }

    @Override
    protected String composeAbsPath()
    {
        return FileUtil.postFixPathWithSystemCompatibleFileSeparator(super.composeAbsPath());
    }

    @Override
    protected String specificDirName()
    {
        return DIR_FOR_INSTRUMENT;
    }
}

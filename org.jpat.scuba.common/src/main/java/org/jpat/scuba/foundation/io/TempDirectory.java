package org.jpat.scuba.foundation.io;

public class TempDirectory extends AbstractPathResolver
{
    private static final String DIR_TEMP = "temp";

    public TempDirectory(final boolean cleanUp)
    {
        super(cleanUp);
    }

    @Override
    protected String specificDirName()
    {
        return DIR_TEMP;
    }
}

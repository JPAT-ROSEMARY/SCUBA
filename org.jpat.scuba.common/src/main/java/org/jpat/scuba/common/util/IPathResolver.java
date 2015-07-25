package org.jpat.scuba.common.util;

public interface IPathResolver
{
    String resolve(final String path);

    String relativise(final String absPath);
}

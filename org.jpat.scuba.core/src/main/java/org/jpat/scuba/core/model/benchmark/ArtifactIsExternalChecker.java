package org.jpat.scuba.core.model.benchmark;

import java.util.Arrays;

final class ArtifactIsExternalChecker
{
    private static final int MORE_LILKELY_NOT_EXTERNAL_RATE = 2;

    private ArtifactIsExternalChecker()
    {
        super();
    }

    //TODO: This is naiive impl + false positive. ... polish up
    /** what are the criteria that should be considered to consider an artifact is not external? (1) An external bytecode artifact is one that resides
     * in one of the following parent folders: build, build.*, lib, libs, bin
     * 
     * @param artifactName
     * @param parent
     * @return */
    static boolean checkIsExternal(final String artifactName, final BenchmarkBytecodeContainer parent)
    {
        assert null != artifactName && !artifactName.isEmpty() : "Parameter 'artifactName' of method 'checkIsExternal' must not be empty";
        assert null != parent : "Parameter 'parent' of method 'checkIsExternal' must not be null";

        final String[] tokens = artifactName.split("/");

        if (null == tokens || 0 >= tokens.length)
        {
            return true;
        }
        int rateSoFar = 0;
        for (final String nextToken : tokens)
        {
            if (nextToken.equalsIgnoreCase(parent.getAlias()))
            {
                rateSoFar++;
            }
        }

        assert null != parent.getAlias() && !parent.getAlias().isEmpty() : "'parent.getAlias()' must not be empty";

        if (Arrays.asList(tokens).contains(parent.getAlias().toLowerCase()))
        {
            rateSoFar++;
        }
        if (rateSoFar >= MORE_LILKELY_NOT_EXTERNAL_RATE)
        {
            return false;
        }
        return true;
    }
}

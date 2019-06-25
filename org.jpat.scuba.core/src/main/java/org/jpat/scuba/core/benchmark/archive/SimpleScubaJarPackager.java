package org.jpat.scuba.core.benchmark.archive;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import org.jpat.scuba.common.util.FileUtil;
import org.jpat.scuba.common.util.StringUtil;
import org.jpat.scuba.core.model.benchmark.BytecodeArtifactAnalysed;
import org.jpat.scuba.core.model.benchmark.IArchiveInOut;
import org.jpat.scuba.foundation.io.AbstractPathResolver;
import org.jpat.scuba.foundation.io.JarFilePathResolver;
import org.jpat.scuba.foundation.io.SimpleLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schlichtherle.truezip.file.TFile;
import de.schlichtherle.truezip.file.TFileOutputStream;

public final class SimpleScubaJarPackager implements IArchiveInOut
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleScubaJarPackager.class);

    private final List<BytecodeArtifactAnalysed> bytecodeArtifactsList;
    private final String archivePathOriginal;
    private final AbstractPathResolver jarFilePathResolver;
    private final String outJarFilePath;

    public SimpleScubaJarPackager(final List<BytecodeArtifactAnalysed> binaryArtifacts, final String archiveFilePathOriginal)
    {
        assert null != binaryArtifacts && !binaryArtifacts.isEmpty() : "Parameter 'binaryArtifacts' of 'SimpleScubaJarPackager''s ctor must not be empty";
        assert null != archiveFilePathOriginal : "Parameter 'archiveFilePathOriginal' of 'SimpleScubaJarPackager''s ctor must not be null";

        this.bytecodeArtifactsList = binaryArtifacts;
        this.archivePathOriginal = archiveFilePathOriginal;

        /*if (!FileUtil.isJar(this.archivePathOriginal))
        {
            throw new ArchiveNotJarException("File '" + this.archivePathOriginal + "' is not a Jar archive");
        }*/

        this.jarFilePathResolver = new JarFilePathResolver(true);
        this.outJarFilePath = this.jarFilePathResolver.resolve(this.archivePathOriginal);
    }

    @Override
    public void run()
    {
        if (!FileUtil.isJar(this.archivePathOriginal))
        {
            return;
        }
        packageUp();
    }

    private void packageUp()
    {
        final Manifest manifest = ScubaJarManifestComposer.composeManifest(this.archivePathOriginal);
        if (null == manifest)
        {
            LOGGER.error("Variable 'manifest' is null. Packaging up a Jar process has been aborted");
            return;
        }

        final TFile jarOutputFile = FileUtil.create(this.outJarFilePath);
        try (final JarOutputStream jos = new JarOutputStream(new TFileOutputStream(jarOutputFile), manifest);)
        {
            final Set<String> tmpDirSet = new HashSet<>(this.bytecodeArtifactsList.size());
            this.bytecodeArtifactsList.forEach(t -> packupAsVisiting(jos, tmpDirSet, t, t.getArtifactRelativePath()));
            jos.flush();
        }
        catch (final IOException e)
        {
            SimpleLogger.error(SimpleScubaJarPackager.LOGGER, e, getClass(), "packageUp", this.outJarFilePath);
        }
    }

    private void packupAsVisiting(final JarOutputStream jos, final Set<String> tmpDirSet, final BytecodeArtifactAnalysed nextInstrumentedArtifact,
            final String resolvedDirectoryPath)
    {
        final JarEntry entryDirectory = new JarEntry(resolvedDirectoryPath);
        if (0 == ScubaJarJarConfiguration.METAMANIFEST_PATH.compareTo(entryDirectory.getName()))
        {
            return;
        }
        entryDirectory.setTime(nextInstrumentedArtifact.getBytecodeArtifact().lastModified());
        try
        {
            final String entryDirName = entryDirectory.getName();
            if (!has(tmpDirSet, entryDirName))
            {
                tmpDirSet.add(entryDirName);
                jos.putNextEntry(entryDirectory);
            }
        }
        catch (final ClassCastException | IOException e)
        {
            SimpleLogger.error(LOGGER, e, getClass(), "packupAsVisiting", nextInstrumentedArtifact.getBytecodeArtifact().getAbsolutePath());
        }
        try
        {
            jos.write(nextInstrumentedArtifact.getByteCode());
        }
        catch (final IOException e)
        {
            SimpleLogger.error(LOGGER, e, getClass(), "packupAsVisiting", entryDirectory.getName());
        }
    }

    @SuppressWarnings("static-method")
    private boolean has(final Set<String> tmpDirSet, final String target)
    {
        if (null != tmpDirSet && !tmpDirSet.isEmpty() && StringUtil.assertNotEmpty(target))
        {
            return tmpDirSet.contains(target);
        }
        return false;
    }
}


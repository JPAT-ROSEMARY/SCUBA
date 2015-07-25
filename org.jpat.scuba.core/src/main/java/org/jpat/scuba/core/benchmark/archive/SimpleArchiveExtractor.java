package org.jpat.scuba.core.benchmark.archive;

import java.io.IOException;
import java.io.InputStream;

import org.jpat.scuba.common.util.FileUtil;
import org.jpat.scuba.common.util.IPathResolver;
import org.jpat.scuba.core.model.IBytecodeContainer;
import org.jpat.scuba.core.model.benchmark.IArchiveInOut;
import org.jpat.scuba.foundation.io.SimpleStreamWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schlichtherle.truezip.file.TFile;
import de.schlichtherle.truezip.zip.ZipEntry;
import de.schlichtherle.truezip.zip.ZipFile;

public final class SimpleArchiveExtractor implements IArchiveInOut
{
    private final IBytecodeContainer container;
    private final IPathResolver pathResolver;
    private final TFile bytecodeArchiveFile;

    public SimpleArchiveExtractor(final IBytecodeContainer container, final IPathResolver pathResolver, final TFile bytecodeArchiveFile)
    {
        assert null != container : "Parameter 'container' of 'SimpleArchiveExtractor''s ctor must not be null";
        assert null != pathResolver : "Parameter 'pathResolver' of 'SimpleArchiveExtractor''s ctor must not be null";
        assert null != bytecodeArchiveFile : "Parameter 'bytecodeArchiveFile' of 'SimpleArchiveExtractor''s ctor must not be null";

        this.container = container;
        this.pathResolver = pathResolver;
        this.bytecodeArchiveFile = bytecodeArchiveFile;
    }

    @Override
    public void run()
    {
        this.extract(this.bytecodeArchiveFile);
    }

    private void extract(final TFile bytecodeArchive)
    {
        assert null != bytecodeArchive : "Parameter 'bytecodeArchive' of method 'extract' must not be null";
        assert bytecodeArchive.exists() : "Parameter 'bytecodeArchive' of method 'extract' does not exist";

        final ArchiveEntry2ScubaOuputTransformer zT = new ArchiveEntry2ScubaOuputTransformer(this.container, this.pathResolver);
        zT.trans(bytecodeArchive);
    }

    static final class ArchiveEntry2ScubaOuputTransformer
    {
        private static final Logger LOGGER = LoggerFactory.getLogger(ArchiveEntry2ScubaOuputTransformer.class);

        private final IPathResolver pathResolver;
        private final IBytecodeContainer container;

        protected ArchiveEntry2ScubaOuputTransformer(final IBytecodeContainer container, final IPathResolver pathResolver)
        {
            assert null != container : "Parameter 'container' of 'ZipEntry2ScubaInputTransformer''s ctor must not be null";
            assert null != pathResolver : "Parameter 'pathResolver' of 'ZipEntry2ScubaInputTransformer''s ctor must not be null";

            this.container = container;
            this.pathResolver = pathResolver;
        }

        protected void trans(final TFile bytecodeArchive)
        {
            assert null != bytecodeArchive : "Parameter 'bytecodeArchive' of method 'trans' must not be null";

            try (final ZipFile zipFile = new ZipFile(bytecodeArchive);)
            {
                zipFile.forEach(t -> this.treat(t, zipFile));
            }
            catch (IOException e)
            {
                LOGGER.error("error while reading archive file '{}'", bytecodeArchive);
            }
        }

        private void treat(final ZipEntry next, final ZipFile zipFile)
        {
            assert null != next : "Parameter 'next' of method 'treat' must not be null";
            assert null != zipFile : "Parameter 'zipFile' of method 'treat' must not be null";

            if (next.isDirectory())
            {
                FileUtil.mkdir(this.pathResolver.resolve(next.getName()));
            }
            else
            {
                try (final InputStream nextInputStream = zipFile.getInputStream(next.getName());)
                {
                    final TFile nextEntryFileWritten = SimpleStreamWriter.write(nextInputStream, this.pathResolver.resolve(next.getName()));
                    if (new TFile(next.getName()).isArchive())
                    {
                        new ArchiveEntry2ScubaOuputTransformer(this.container, this.pathResolver).trans(nextEntryFileWritten);
                    }
                    else
                    {
                        this.container.addBytecode4(next.getName(), nextEntryFileWritten);
                    }
                }
                catch (IOException e)
                {
                    LOGGER.error("error while having an inputStram from the next entry in the archive file", e);
                }
            }
        }
    }
}

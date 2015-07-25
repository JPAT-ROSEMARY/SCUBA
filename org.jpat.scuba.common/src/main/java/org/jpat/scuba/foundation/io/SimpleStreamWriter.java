package org.jpat.scuba.foundation.io;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.jpat.scuba.common.util.ConstantsCommon;
import org.jpat.scuba.common.util.FileUtil;
import org.jpat.scuba.common.util.IPathResolver;
import org.jpat.scuba.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schlichtherle.truezip.file.TFile;
import de.schlichtherle.truezip.file.TFileOutputStream;

public final class SimpleStreamWriter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleStreamWriter.class);
    private static final String WRITE_METHOD = "'write'";

    private SimpleStreamWriter()
    {
        super();
    }

    public static TFile write(final String relativefilePath, final byte[] bytes, final IPathResolver pathResolver)
    {
        assert null != bytes : "Parameter 'bytes' of method 'write' must not be null";
        assert null != relativefilePath && !relativefilePath.isEmpty() : "Parameter 'relativefilePath' of method 'write' must not be empty";
        assert null != pathResolver : "Parameter 'pathResolver' of method 'write' must not be null";

        final TFile file = new TFile(relativefilePath);
        if (StringUtil.assertNotEmpty(file.getName()) && ConstantsCommon.ZERO_LENGTH < bytes.length)
        {
            String targetFileAbsoluterPath = relativefilePath;
            if (!new TFile(relativefilePath).isAbsolute())
            {
                targetFileAbsoluterPath = pathResolver.resolve(relativefilePath);
            }
            return writeInternal(targetFileAbsoluterPath, bytes);
        }
        return file;
    }

    public static TFile write(final InputStream inputStream, final String filePath)
    {
        assert null != inputStream : "'inputStream' of method 'write' must not be null";
        assert null != filePath && !filePath.isEmpty() : "Parameter 'filePath' of method 'write' must not be empty";
        assert new TFile(filePath).isAbsolute() : "Parameter 'filePath' should be absolute";

        try
        {
            return writeInternal(filePath, IOUtils.toByteArray(inputStream));
        }
        catch (final IOException e)
        {
            SimpleLogger.error(SimpleStreamWriter.LOGGER, e, SimpleStreamWriter.class, WRITE_METHOD, filePath);
            return null;
        }
    }

    private static TFile writeInternal(final String absoluteFilePath, final byte[] bytes)
    {
        assert null != absoluteFilePath && !absoluteFilePath.isEmpty() : "Parameter 'absoluteFilePath' of method 'writeInternal' must not be empty";
        assert null != bytes : "Parameter 'bytes' of method 'writeInternal' must not be null";

        final TFile absFile = new TFile(absoluteFilePath);

        if (!absFile.exists() || !absFile.getParentFile().exists())
        {
            absFile.getParentFile().mkdir();
            FileUtil.create(absFile);
        }

        try (OutputStream os = new BufferedOutputStream(new TFileOutputStream(absFile));)
        {
            IOUtils.write(bytes, os);
        }
        catch (final IOException e)
        {
            SimpleLogger.error(SimpleStreamWriter.LOGGER, e, SimpleStreamWriter.class, WRITE_METHOD, absoluteFilePath);
        }
        return absFile;
    }
}

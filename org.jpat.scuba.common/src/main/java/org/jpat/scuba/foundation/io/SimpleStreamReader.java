package org.jpat.scuba.foundation.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.jpat.scuba.common.util.ConstantsCommon;
import org.jpat.scuba.common.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.schlichtherle.truezip.file.TFile;

public class SimpleStreamReader
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleStreamReader.class);

    private SimpleStreamReader()
    {
        super();
    }

    public static InputStream read(final TFile nextFile)
    {
        assert null != nextFile : "Parameter 'nextFile' of method 'read' must not be null";
        final String absPath = FileUtil.convertRelativeToAbs(nextFile.getPath());
        assert null != absPath && !absPath.isEmpty() : "Parameter 'absPath' of method 'read' must not be empty";
        try (FileInputStream is = new FileInputStream(new TFile(absPath));)
        {
            if (ConstantsCommon.ZERO_LENGTH < is.available())
            {
                return IOUtils.toBufferedInputStream(is);
            }
        }
        catch (final FileNotFoundException e)
        {
            SimpleLogger.error(SimpleStreamReader.LOGGER, e, SimpleStreamReader.class, "read", nextFile.getAbsolutePath());
        }
        catch (final ArrayIndexOutOfBoundsException e)
        {
            SimpleLogger.error(SimpleStreamReader.LOGGER, e, SimpleStreamReader.class, "read", nextFile.getAbsolutePath());
        }
        catch (final IOException e)
        {
            SimpleLogger.error(SimpleStreamReader.LOGGER, e, SimpleStreamReader.class, "read", nextFile.getAbsolutePath());
        }
        return null;
    }
}

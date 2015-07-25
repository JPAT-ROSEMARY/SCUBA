package org.jpat.scuba.foundation.exceptions;

import de.schlichtherle.truezip.file.TFile;

@SuppressWarnings("serial")
public final class NotArchiveException extends AbstractScubaRuntimeException
{
    public NotArchiveException(final TFile file)
    {
        super("File '" + file + "' is not archive");
    }
}

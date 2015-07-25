package org.jpat.scuba.core.model.benchmark;

import org.jpat.scuba.common.util.FileUtil;
import org.jpat.scuba.common.util.ISelfIdentifier;

import de.schlichtherle.truezip.file.TFile;

public enum AcceptedFileType implements ISelfIdentifier
{
    DAT(".dat"), CLASS(".class");

    private final String technicalValue;

    private AcceptedFileType(final String technicalVale)
    {
        this.technicalValue = technicalVale;
    }

    @Override
    public String getTechnicalValue()
    {
        return this.technicalValue;
    }

    @Override
    public String getNaturalValue()
    {
        return this.technicalValue;
    }

    public static boolean isExcludedFileType(final TFile file)
    {
        assert null != file : "Parameter 'file' of method 'isExcludedFileType' must not be null";

        final String subjectExtension = FileUtil.extractFileExtension(file);
        for (final AcceptedFileType next : values())
        {
            if (0 == next.getTechnicalValue().compareTo(subjectExtension))
            {
                return false;
            }
        }
        return true;
    }
}

package org.jpat.scuba.common.util;

import de.schlichtherle.truezip.file.TFile;

public enum FileType implements ISelfIdentifier
{
    ZIP("zip", "Zip Archive file", Category.ARCHIVE),
    JAR("jar", "Jar archive file", Category.ARCHIVE),
    DIRECTORY("Directory", "Directory", Category.DIRECTORY),
    NOT_SUPPORTED("Not-supported", "Not supported file type", Category.DIRECTORY);

    private final String technoName;
    private final String naturalName;
    private final Category category;

    private FileType(final String technocalName, final String naturalName, final Category category)
    {
        assert null != naturalName && !naturalName.isEmpty() : "Parameter 'naturalName' of method 'FileType' must not be empty";
        assert null != technocalName && !technocalName.isEmpty() : "Parameter 'technocalName' of method 'FileType' must not be empty";
        assert null != category : "Parameter 'category' of method 'FileType' must not be null";

        this.technoName = technocalName;
        this.naturalName = naturalName;
        this.category = category;
    }

    @Override
    public String getTechnicalValue()
    {
        return this.technoName;
    }

    @Override
    public String getNaturalValue()
    {
        return this.naturalName;
    }

    public static FileType resolve(final TFile file)
    {
        if (file.isDirectory() && !FileUtil.isArchive(file))
        {
            return DIRECTORY;
        }
        if (FileUtil.isArchive(file))
        {
            for (final FileType next : values())
            {
                if (0 == next.getTechnicalValue().compareTo(FileUtil.extractFileExtension(file)))
                {
                    return next;
                }
            }
        }
        return NOT_SUPPORTED;
    }

    public Category getCategory()
    {
        return this.category;
    }
}

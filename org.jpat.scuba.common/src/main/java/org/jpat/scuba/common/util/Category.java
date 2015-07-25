package org.jpat.scuba.common.util;

public enum Category implements ISelfIdentifier
{
    ARCHIVE("Archive", "Archive"), DIRECTORY("Directory", "Directory"), NOT_SUPPORTED("Not-supported", "Not supported file type");

    private final String technoName;
    private final String naturalName;

    private Category(final String technocalName, final String naturalName)
    {
        assert null != naturalName && !naturalName.isEmpty() : "Parameter 'naturalName' of method 'FileType' must not be empty";
        assert null != technocalName && !technocalName.isEmpty() : "Parameter 'technocalName' of method 'FileType' must not be empty";

        this.technoName = technocalName;
        this.naturalName = naturalName;
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
}

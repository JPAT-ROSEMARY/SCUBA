package org.jpat.scuba.core.model;

import org.jpat.scuba.common.util.ISelfIdentifier;

public enum ExternalTreatmentChoice implements ISelfIdentifier
{
    EXCLUDED("External's excluded", "Won't be considered for the analysis"), 
    INCLUDED("External's included", "Should be considered for the analysis");

    private final String name;
    private final String detail;

    private ExternalTreatmentChoice(final String name, final String detail)
    {
        assert null != detail && !detail.isEmpty() : "Parameter 'detail' of 'ExternalTreatmentChoice''s ctor must not be empty";
        assert null != name && !name.isEmpty() : "Parameter 'name' of 'ExternalTreatmentChoice''s ctor must not be empty";
        this.name = name;
        this.detail = detail;
    }

    @Override
    public String getTechnicalValue()
    {
        return this.name;
    }

    @Override
    public String getNaturalValue()
    {
        return this.detail;
    }
}

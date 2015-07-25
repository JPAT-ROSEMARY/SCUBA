package org.jpat.scuba.core.model.analysis;

import org.jpat.scuba.common.util.ISelfIdentifier;

public enum Category implements ISelfIdentifier
{
    STATS("Stats"), RUNTIME_STATS("Runtime-Stats"), COUPLING("Coupling"), CYCLE("Cycle");

    private final String naturalValue;

    private Category(final String naturalValue)
    {
        assert null != naturalValue && !naturalValue.isEmpty() : "Parameter 'naturalValue' of method 'Category' must not be empty";
        this.naturalValue = naturalValue;
    }

    @Override
    public String getTechnicalValue()
    {
        return this.name();
    }

    @Override
    public String getNaturalValue()
    {
        return this.naturalValue;
    }
}
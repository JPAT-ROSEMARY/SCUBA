package org.jpat.scuba.core.model.analysis;

public final class AnalysisIdIDetail
{
    public AnalysisIdIDetail(final String naturalValue, final String detail, final Category category)
    {
        assert null != naturalValue && !naturalValue.isEmpty() : "Parameter 'naturalValue' of 'AnalysisIdIDetail''s ctor must not be empty";
        assert null != detail && !detail.isEmpty() : "Parameter 'detail' of 'AnalysisIdIDetail''s ctor must not be empty";
        assert null != category : "Parameter 'category' of 'AnalysisIdIDetail''s ctor must not be null";

        this.naturalVal = naturalValue;
        this.detail = detail;
        this.category = category;
    }

    private final String naturalVal;
    private final String detail;
    private final Category category;

    public String getNaturalValue()
    {
        return this.naturalVal;
    }

    public Category getCategory()
    {
        return this.category;
    }

    public String getDetail()
    {
        return this.detail;
    }
}

package org.jpat.scuba.core.model.analysis;

public enum BadAnalyserId implements IAnalyserId
{
    BAD_ANALYSER_ID("BAD_ANALYSER_ID", "BadAnalyserId - detail", Category.STATS);

    private final AnalysisIdIDetail implementation;

    private BadAnalyserId(final String presentationName, final String detail, final Category category)
    {
        this.implementation = new AnalysisIdIDetail(presentationName, detail, category);
    }

    @Override
    public String getTechnicalValue()
    {
        return null;
    }

    @Override
    public String getNaturalValue()
    {
        return this.implementation.getNaturalValue();
    }

    @Override
    public Category getCategory()
    {
        return this.implementation.getCategory();
    }

    @Override
    public String getDetail()
    {
        return this.implementation.getDetail();
    }

}

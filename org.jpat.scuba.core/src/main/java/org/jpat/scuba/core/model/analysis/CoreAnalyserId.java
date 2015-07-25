package org.jpat.scuba.core.model.analysis;

public enum CoreAnalyserId implements IAnalyserId
{
    SAMPLE_ANALYSIS_Id(
                       "sample analysis id",
                       "Core sample analysis id detail",
                       Category.STATS),
    //Future Work
    CLASS_LEVEL_DEPENDENCY_ANALYSER(
                                    "Dependency analysis at the class level",
                                    "Computes the dependencies between classes",
                                    Category.COUPLING),
    //Future Work
    CLASS_LEVEL_CYCLES("Cycle analysis at the class-level", "Finds out classes who make a cyclic dependency (Completely coupled)", Category.COUPLING);
                                    
    private final AnalysisIdIDetail detail;

    private CoreAnalyserId(final String naturalVal, final String detail, final Category category)
    {
        this.detail = new AnalysisIdIDetail(naturalVal, detail, category);
    }

    @Override
    public Category getCategory()
    {
        return this.detail.getCategory();
    }

    @Override
    public String getDetail()
    {
        return this.detail.getDetail();
    }

    @Override
    public String getTechnicalValue()
    {
        return name();
    }

    @Override
    public String getNaturalValue()
    {
        return this.detail.getNaturalValue();
    }
}

package org.jpat.scuba.analysisprovider.statica.bcel.model;

import org.jpat.scuba.core.model.analysis.AnalysisIdIDetail;
import org.jpat.scuba.core.model.analysis.Category;
import org.jpat.scuba.core.model.analysis.IAnalyserId;

public enum BCELAnalyserId implements IAnalyserId
{
    SAMPLE_ANALYSIS_Id(
                       "sample analysis id",
                       "BCEL sample analysis id detail",
                       Category.STATS),

    INTERFACE_METHOD_INVOKATION_COUNTER(
                                        "Interface method invokation stats analysis",
                                        "statically analyse bytecode files to count interface method invocations using BCEL Framework",
                                        Category.STATS);

    private final AnalysisIdIDetail implementation;

    private BCELAnalyserId(final String presentationName, final String detail, final Category category)
    {
        this.implementation = new AnalysisIdIDetail(presentationName, detail, category);
    }

    @Override
    public String getDetail()
    {
        return this.implementation.getDetail();
    }

    @Override
    public String getTechnicalValue()
    {
        return name();
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
}

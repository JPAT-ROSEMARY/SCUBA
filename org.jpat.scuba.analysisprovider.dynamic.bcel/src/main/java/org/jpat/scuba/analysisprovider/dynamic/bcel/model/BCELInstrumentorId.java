package org.jpat.scuba.analysisprovider.dynamic.bcel.model;

import org.jpat.scuba.core.model.analysis.AnalysisIdIDetail;
import org.jpat.scuba.core.model.analysis.Category;
import org.jpat.scuba.core.model.analysis.IAnalyserId;

public enum BCELInstrumentorId implements IAnalyserId
{
    INTERFACE_METHOD_INVOKATION_INSTRUMENTOR(
                                             "Interface method invokations instrumented",
                                             "instrument interface method invocations in the bytecode files using BCEL Framework",
                                             Category.RUNTIME_STATS);

    private final AnalysisIdIDetail implementation;

    private BCELInstrumentorId(final String presentationName, final String detail, final Category category)
    {
        this.implementation = new AnalysisIdIDetail(presentationName, detail, category);
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
}

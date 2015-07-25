package org.jpat.scuba.analysisprovider.dynamic.asm.model;

import org.jpat.scuba.core.model.analysis.AnalysisIdIDetail;
import org.jpat.scuba.core.model.analysis.Category;
import org.jpat.scuba.core.model.analysis.IAnalyserId;

public enum ASMInstrumentorId implements IAnalyserId
{
    INTERFACE_METHOD_INVOKATION_INSTRUMENTOR(
                                             "Interface method invokations instrumented",
                                             "instrument interface method invocations in the bytecode files using ASM Framework",
                                             Category.RUNTIME_STATS);

    private final AnalysisIdIDetail implementation;

    private ASMInstrumentorId(final String presentationName, final String detail, final Category category)
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

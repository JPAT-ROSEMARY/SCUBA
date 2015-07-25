package org.jpat.scuba.analysisprovider.statica.asm.model;

import org.jpat.scuba.core.model.analysis.AnalysisIdIDetail;
import org.jpat.scuba.core.model.analysis.Category;
import org.jpat.scuba.core.model.analysis.IAnalyserId;

public enum ASMAnalyserId implements IAnalyserId
{
    INTERFACE_METHOD_INVOKATION_COUNTER(
                                        "Interface method invokation stats analysis",
                                        "statically analyse the bytecode files to count interface method invocations using ASM Framework",
                                        Category.STATS);

    private final AnalysisIdIDetail implementation;

    private ASMAnalyserId(final String presentationName, final String detail, final Category category)
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

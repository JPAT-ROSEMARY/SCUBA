package org.jpat.scuba.core.foundation;

public final class Dynamic extends AnalysisKind
{
    public static final AnalysisKind INSTANCE = new Dynamic();

    private Dynamic()
    {
        super();
    }

    @Override
    public String getTechnicalValue()
    {
        return "dynamic";
    }

    @Override
    public String getNaturalValue()
    {
        return "Instrumentation";
    }

    @Override
    public String getDetail()
    {
        return "Instrumentation for dynamic analysis";
    }
}

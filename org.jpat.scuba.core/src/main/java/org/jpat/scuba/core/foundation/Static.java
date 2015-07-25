package org.jpat.scuba.core.foundation;

public final class Static extends AnalysisKind
{
    public static final Static INSTANCE = new Static();

    private Static()
    {
        super();
    }

    @Override
    public String getTechnicalValue()
    {
        return "static";
    }

    @Override
    public String getNaturalValue()
    {
        return "Static";
    }

    @Override
    public String getDetail()
    {
        return "Static Analysis";
    }
}

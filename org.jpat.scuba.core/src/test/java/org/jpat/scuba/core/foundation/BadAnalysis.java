package org.jpat.scuba.core.foundation;

public final class BadAnalysis extends Analysis
{
    public static final BadAnalysis INSTANCE = new BadAnalysis();

    private BadAnalysis()
    {
        super(BadLanguage.INSTANCE, BadAnalysisKind.INSTANCE);
    }

    @Override
    public String getTechnicalValue()
    {
        return "Bad Analysis";
    }

    @Override
    public String getNaturalValue()
    {
        return "Bad Analysis - Natural value ";
    }

    @Override
    public String getDetail()
    {
        return "Bad Analysis - detail";
    }
}

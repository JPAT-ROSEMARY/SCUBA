package org.jpat.scuba.core.foundation;


public final class BadAnalysisKind extends AnalysisKind
{
    public static final BadAnalysisKind INSTANCE = new BadAnalysisKind();
    
    private BadAnalysisKind()
    {
        super();
    }
    @Override
    public String getTechnicalValue()
    {
        return "Bad Analysis Kind";
    }

    @Override
    public String getNaturalValue()
    {
        return "Bad Analysis Kind - presentation value";
    }

    @Override
    public String getDetail()
    {
        return "Bad Analysis Kind - detail";
    }
}

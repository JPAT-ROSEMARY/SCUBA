package org.jpat.scuba.core.foundation;

public final class BadLanguage extends Language
{
    public static final BadLanguage INSTANCE = new BadLanguage();

    private BadLanguage()
    {
        super();
    }

    @Override
    public String getTechnicalValue()
    {
        return "Bad Language";
    }

    @Override
    public String getNaturalValue()
    {
        return "Bad Language - Presentation value";
    }

    @Override
    public String getDetail()
    {
        return "Bad Language - description";
    }
}

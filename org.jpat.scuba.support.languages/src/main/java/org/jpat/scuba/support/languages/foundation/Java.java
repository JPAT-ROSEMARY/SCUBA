package org.jpat.scuba.support.languages.foundation;

import org.jpat.scuba.core.foundation.Language;

public final class Java extends Language
{
    public static final Java INSTANCE = new Java();

    private Java()
    {
        super();
    }

    @Override
    public String getTechnicalValue()
    {
        return "java";
    }

    @Override
    public String getNaturalValue()
    {
        return "Java";
    }

    @Override
    public String getDetail()
    {
        return "Java Language";
    }
}

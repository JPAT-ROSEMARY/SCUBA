package org.jpat.scuba.support.languages.foundation;

import org.jpat.scuba.core.foundation.Language;

public final class Scala extends Language
{
    public static final Scala INSTANCE = new Scala();

    private Scala()
    {
        super();
    }

    @Override
    public String getTechnicalValue()
    {
        return "scala";
    }

    @Override
    public String getNaturalValue()
    {
        return "Scala";
    }

    @Override
    public String getDetail()
    {
        return "Scala Langauge";
    }
}

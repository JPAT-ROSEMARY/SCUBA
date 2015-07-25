package org.jpat.scuba.core.foundation;

import org.jpat.scuba.common.util.ISelfIdentifier;
import org.jpat.scuba.common.util.StringUtil;

public abstract class Analysis implements ISelfIdentifier
{
    protected final Language language;
    protected final AnalysisKind analysisKind;

    protected Analysis(final Language language, final AnalysisKind analysisKind)
    {
        assert null != analysisKind : "Parameter 'analysisKind' of 'Analysis''s ctor must not be null";
        assert null != language : "Parameter 'language' of 'Analysis''s ctor must not be null";

        this.language = language;
        this.analysisKind = analysisKind;
    }

    public abstract String getDetail();

    public final Language getLanguage()
    {
        return this.language;
    }

    public final AnalysisKind getKind()
    {
        return this.analysisKind;
    }

    public Analysis resolve(final String analysisProvider, final String analysisKind, final String language)
    {
        assert null != analysisProvider && !analysisProvider.isEmpty() : "Parameter 'analysisProvider' of method 'resolve' must not be empty";
        assert null != analysisKind && !analysisKind.isEmpty() : "Parameter 'analysisKind' of method 'resolve' must not be empty";
        assert null != language && !language.isEmpty() : "Parameter 'language' of method 'resolve' must not be empty";

        return 0 == getTechnicalValue().compareToIgnoreCase(StringUtil.composeWithSpaceInBetween(analysisProvider, language, analysisKind)) ? this
                : null;
    }

    @Override
    public String toString()
    {
        return getNaturalValue();
    }
}

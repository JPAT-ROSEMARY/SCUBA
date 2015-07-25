package org.jpat.scuba.core.model;

import org.jpat.scuba.core.foundation.Analysis;
import org.jpat.scuba.core.foundation.Language;

public interface IAnalysisProvider extends IExtension
{
    Analysis getAnalysis();

    Language getLanguage();

    /**
     * derives analysis from concrete analysisProvider
     * according to the provided configuration/preferences
     * 
     * @param analysisProvider
     *          the name of the analysis provider
     * @param analysisKind 
     *           one of two : (static or dynamic)
     *          
     * @param language
     *          Java or Scala
     * @return
     *        specific analysis which is the one matching the analysis kind and language choices
     */
    Analysis deriveAnalysis(final String analysisProvider, final String analysisKind, String language);

    boolean isPostAnalysisRequired();
}

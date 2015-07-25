package org.jpat.scuba.analysisprovider.statica.bcel.foundation;

import org.jpat.scuba.common.util.StringUtil;
import org.jpat.scuba.core.foundation.Analysis;
import org.jpat.scuba.core.foundation.Static;
import org.jpat.scuba.support.languages.foundation.Java;

/**
 * By default the analysis provider and analysis types are configured with Java language, unless otherwise specified.
 */
public class BCELStaticAnalysis extends Analysis
{
    public static final BCELStaticAnalysis INSTANCE = new BCELStaticAnalysis();

    private BCELStaticAnalysis()
    {
        super(Java.INSTANCE, Static.INSTANCE);
    }

    @Override
    public String getTechnicalValue()
    {
        return StringUtil.composeWithSpaceInBetween("BCEL", super.language.getTechnicalValue(), super.analysisKind.getTechnicalValue());
    }

    @Override
    public String getDetail()
    {
        return "Bytecode Static Analysis suite based on BCEL Framework";
    }

    @Override
    public String getNaturalValue()
    {
        return "BCEL-based bytecode Static Analysis";
    }
}

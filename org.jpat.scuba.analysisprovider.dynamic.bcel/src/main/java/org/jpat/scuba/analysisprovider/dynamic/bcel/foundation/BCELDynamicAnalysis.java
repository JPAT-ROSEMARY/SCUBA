package org.jpat.scuba.analysisprovider.dynamic.bcel.foundation;

import org.jpat.scuba.common.util.StringUtil;
import org.jpat.scuba.core.foundation.Analysis;
import org.jpat.scuba.core.foundation.Dynamic;
import org.jpat.scuba.support.languages.foundation.Java;

/**
 * By default the analysis provider and analysis types are configured with Java language, unless otherwise specified.
 */
public class BCELDynamicAnalysis extends Analysis
{
    public static final BCELDynamicAnalysis INSTANCE = new BCELDynamicAnalysis();

    private BCELDynamicAnalysis()
    {
        super(Java.INSTANCE, Dynamic.INSTANCE);
    }

    @Override
    public String getTechnicalValue()
    {
        return StringUtil.composeWithSpaceInBetween("BCEL", super.language.getTechnicalValue(), super.analysisKind.getTechnicalValue());
    }

    @Override
    public String getNaturalValue()
    {
        return "BCEL-based bytecode Dynamic Analysis";
    }

    @Override
    public String getDetail()
    {
        return "Bytecode Dynamic Analysis suite based on BCEL Framework";
    }
}

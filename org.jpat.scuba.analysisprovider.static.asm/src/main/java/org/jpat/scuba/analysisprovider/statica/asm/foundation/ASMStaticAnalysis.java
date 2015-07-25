package org.jpat.scuba.analysisprovider.statica.asm.foundation;

import org.jpat.scuba.common.util.StringUtil;
import org.jpat.scuba.core.foundation.Analysis;
import org.jpat.scuba.core.foundation.Static;
import org.jpat.scuba.support.languages.foundation.Java;

/**
 * By default the analysis provider and analysis types are configured with Java language, unless otherwise specified.
 */
public final class ASMStaticAnalysis extends Analysis
{
    public static final ASMStaticAnalysis INSTANCE = new ASMStaticAnalysis();

    private ASMStaticAnalysis()
    {
        super(Java.INSTANCE, Static.INSTANCE);
    }

    @Override
    public String getTechnicalValue()
    {
        return StringUtil.composeWithSpaceInBetween("ASM", this.language.getTechnicalValue(), this.analysisKind.getTechnicalValue());
    }

    @Override
    public String getDetail()
    {
        return "Java bytecode Static Analysis suite based on ASM Framework";
    }

    @Override
    public String getNaturalValue()
    {
        return "ASM-based Java bytecode Static Analysis";
    }
}

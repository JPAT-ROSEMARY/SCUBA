package org.jpat.scuba.analysisprovider.dynamic.asm.foundation;

import org.jpat.scuba.common.util.StringUtil;
import org.jpat.scuba.core.foundation.Analysis;
import org.jpat.scuba.core.foundation.Dynamic;
import org.jpat.scuba.support.languages.foundation.Java;

/**
 * By default the analysis provider and analysis types are configured with Java language, unless otherwise specified.
 */
public final class ASMDynamicAnalysis extends Analysis
{
    public static final ASMDynamicAnalysis INSTANCE = new ASMDynamicAnalysis();

    private ASMDynamicAnalysis()
    {
        super(Java.INSTANCE, Dynamic.INSTANCE);
    }

    @Override
    public String getTechnicalValue()
    {
        return StringUtil.composeWithSpaceInBetween("ASM", this.language.getTechnicalValue(), this.analysisKind.getTechnicalValue());
    }

    @Override
    public String getNaturalValue()
    {
        return "ASM-based bytecode Dynamic Analysis";
    }

    @Override
    public String getDetail()
    {
        return "Bytecode Dynamic Analysis suite based on ASM Framework";
    }
}

package org.jpat.scuba.analysisprovider.statica.asm.scala.foundation;

import org.jpat.scuba.common.util.StringUtil;
import org.jpat.scuba.core.foundation.Analysis;
import org.jpat.scuba.core.foundation.Static;
import org.jpat.scuba.support.languages.foundation.Scala;

public final class ASMScalaStaticAnalysis extends Analysis
{
    public static final ASMScalaStaticAnalysis INSTANCE = new ASMScalaStaticAnalysis();

    public ASMScalaStaticAnalysis()
    {
        super(Scala.INSTANCE, Static.INSTANCE);
    }

    @Override
    public String getTechnicalValue()
    {
        return StringUtil.composeWithSpaceInBetween("ASM", this.language.getTechnicalValue(), this.analysisKind.getTechnicalValue());
    }

    @Override
    public String getNaturalValue()
    {
        return "ASM-based Scala bytecode Static Analysis";
    }

    @Override
    public String getDetail()
    {
        return "Scala bytecode Static Analysis suite based on ASM Framework";
    }
}

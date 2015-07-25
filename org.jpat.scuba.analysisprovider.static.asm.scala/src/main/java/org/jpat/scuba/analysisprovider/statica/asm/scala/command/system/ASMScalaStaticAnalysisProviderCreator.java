package org.jpat.scuba.analysisprovider.statica.asm.scala.command.system;

import org.jpat.scuba.analysisprovider.statica.asm.scala.controller.ASMScalaStaticAnalysisProvider;
import org.jpat.scuba.core.model.IAnalysisProvider;

public final class ASMScalaStaticAnalysisProviderCreator
{
    private ASMScalaStaticAnalysisProviderCreator()
    {
        super();
    }

    public static IAnalysisProvider create()
    {
        return new ASMScalaStaticAnalysisProvider();
    }
}

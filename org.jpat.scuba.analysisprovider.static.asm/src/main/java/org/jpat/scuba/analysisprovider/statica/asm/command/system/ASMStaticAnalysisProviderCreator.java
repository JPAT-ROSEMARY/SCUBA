package org.jpat.scuba.analysisprovider.statica.asm.command.system;

import org.jpat.scuba.analysisprovider.statica.asm.controller.ASMStaticAnalysisProvider;
import org.jpat.scuba.core.model.IAnalysisProvider;

public final class ASMStaticAnalysisProviderCreator
{
    private ASMStaticAnalysisProviderCreator()
    {
        super();
    }

    public static IAnalysisProvider create()
    {
        return new ASMStaticAnalysisProvider();
    }
}

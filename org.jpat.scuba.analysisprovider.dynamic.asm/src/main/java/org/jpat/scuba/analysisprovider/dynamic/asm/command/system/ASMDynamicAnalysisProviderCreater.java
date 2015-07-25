package org.jpat.scuba.analysisprovider.dynamic.asm.command.system;

import org.jpat.scuba.analysisprovider.dynamic.asm.controller.ASMDynamicAnalysisProvider;
import org.jpat.scuba.core.model.IAnalysisProvider;

public final class ASMDynamicAnalysisProviderCreater
{
    private ASMDynamicAnalysisProviderCreater()
    {
        super();
    }
    
    public static IAnalysisProvider create()
    {
        return new ASMDynamicAnalysisProvider();
    }
}

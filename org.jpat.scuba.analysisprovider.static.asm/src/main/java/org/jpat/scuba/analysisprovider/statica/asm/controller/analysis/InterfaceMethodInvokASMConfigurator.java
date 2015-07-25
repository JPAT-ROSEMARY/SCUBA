package org.jpat.scuba.analysisprovider.statica.asm.controller.analysis;

import org.jpat.scuba.core.model.analysis.config.InterfaceMethodInvokConfigurator;
import org.objectweb.asm.Opcodes;

public final class InterfaceMethodInvokASMConfigurator extends InterfaceMethodInvokConfigurator
{
    public InterfaceMethodInvokASMConfigurator()
    {
        super(Opcodes.INVOKEINTERFACE);
    }
}

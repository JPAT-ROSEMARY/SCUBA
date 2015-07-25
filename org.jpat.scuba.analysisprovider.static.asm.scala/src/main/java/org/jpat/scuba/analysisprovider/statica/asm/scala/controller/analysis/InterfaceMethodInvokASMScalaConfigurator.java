package org.jpat.scuba.analysisprovider.statica.asm.scala.controller.analysis;

import org.jpat.scuba.core.model.analysis.config.InterfaceMethodInvokConfigurator;

import scala.tools.asm.Opcodes;

public final class InterfaceMethodInvokASMScalaConfigurator extends InterfaceMethodInvokConfigurator
{
    public InterfaceMethodInvokASMScalaConfigurator()
    {
        super(Opcodes.INVOKEINTERFACE);
    }
}

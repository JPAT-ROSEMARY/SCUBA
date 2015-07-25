package org.jpat.scuba.analysisprovider.statica.bcel.controller.analysis;

import org.apache.bcel.Constants;
import org.jpat.scuba.core.model.analysis.config.InterfaceMethodInvokConfigurator;

public final class InterfaceMethodInvokBCELConfigurator extends InterfaceMethodInvokConfigurator
{
    public InterfaceMethodInvokBCELConfigurator()
    {
        super(Constants.INVOKEINTERFACE);
    }
}

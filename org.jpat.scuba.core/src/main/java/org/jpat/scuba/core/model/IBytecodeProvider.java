package org.jpat.scuba.core.model;

import org.jpat.scuba.core.controller.benchmark.BytecodeProviderExtension;

public interface IBytecodeProvider extends IExtension
{
    BytecodeProviderExtension createBytecodeProviderExtension();
}

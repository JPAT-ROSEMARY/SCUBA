package org.jpat.scuba.core.model;

import de.schlichtherle.truezip.file.TFile;

public interface IScubaController extends IExtension
{
    FANChallenge getFANChallenge();

    IBytecodeContainer getBytecodeContainer();

    TFile getBenchmarkExampleBytecodePlace();
    
    String getBenchmarkExampleName();

    void instantiateBytecodeProvider(final IBytecodeProvider bytecodeProvider);
}
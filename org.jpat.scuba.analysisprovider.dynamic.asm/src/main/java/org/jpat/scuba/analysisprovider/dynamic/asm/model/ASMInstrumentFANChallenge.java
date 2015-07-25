package org.jpat.scuba.analysisprovider.dynamic.asm.model;

import org.jpat.scuba.core.model.FANChallenge;

import de.schlichtherle.truezip.file.TFile;

public class ASMInstrumentFANChallenge extends FANChallenge
{
    public ASMInstrumentFANChallenge(final TFile file, final String name)
    {
        super(file, name, "ASM Dynamic SCUBA");
    }
}

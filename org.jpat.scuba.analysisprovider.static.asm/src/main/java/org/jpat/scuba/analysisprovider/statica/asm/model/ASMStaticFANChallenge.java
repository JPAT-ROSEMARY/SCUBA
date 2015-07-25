package org.jpat.scuba.analysisprovider.statica.asm.model;

import org.jpat.scuba.core.model.FANChallenge;

import de.schlichtherle.truezip.file.TFile;

public final class ASMStaticFANChallenge extends FANChallenge
{
    public ASMStaticFANChallenge(final TFile file, final String name)
    {
        super(file, name, "ASM Static SCUBA");
    }
}

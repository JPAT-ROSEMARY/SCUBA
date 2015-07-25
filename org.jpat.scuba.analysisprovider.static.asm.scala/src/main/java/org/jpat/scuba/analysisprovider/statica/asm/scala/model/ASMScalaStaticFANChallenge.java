package org.jpat.scuba.analysisprovider.statica.asm.scala.model;

import org.jpat.scuba.core.model.FANChallenge;

import de.schlichtherle.truezip.file.TFile;

public final class ASMScalaStaticFANChallenge extends FANChallenge
{
    public ASMScalaStaticFANChallenge(final TFile file, final String name)
    {
        super(file, name, "ASM Scala Static SCUBA");
    }
}

package org.jpat.scuba.analysisprovider.statica.bcel.model;

import org.jpat.scuba.core.model.FANChallenge;

import de.schlichtherle.truezip.file.TFile;

public final class BCELStaticFANChallenge extends FANChallenge
{
    public BCELStaticFANChallenge(final TFile file, final String name)
    {
        super(file, name, "BCEL Static SCUBA");
    }
}

package org.jpat.scuba.analysisprovider.dynamic.bcel.model;

import org.jpat.scuba.core.model.FANChallenge;

import de.schlichtherle.truezip.file.TFile;

public class BCELInstrumentFANChallenge extends FANChallenge
{
    public BCELInstrumentFANChallenge(final TFile file, final String name)
    {
        super(file, name, "BCEL Dynamic SCUBA");
    }
}

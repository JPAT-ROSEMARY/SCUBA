package org.jpat.scuba.core.controller.analysis;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.jpat.scuba.core.controller.AnalysisProvider;
import org.jpat.scuba.core.foundation.BadAnalysis;
import org.jpat.scuba.core.foundation.Constants;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.analysis.BadAnalyserId;
import org.jpat.scuba.core.model.analysis.IAnalyserId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import de.schlichtherle.truezip.file.TFile;

public class AnalysisExtensionTest extends MockitoSampleBaseTestCase
{
    @Mock private AnalysisProvider analysisProvider;

    private Set<IAnalyserId> analyserIds;
    private String name;
    private TFile file;
    private FANChallenge fanChallenge;
    private AnalysisExtension analysisExtension;

    @Before
    public void setup()
    {
        this.file =  Constants.TEST_BENCHMARK_EXAMPLE_FILE;
        this.name = new String(Constants.TEST_BENCHMARK_EXAMPLE);
        this.fanChallenge = new FANChallenge(this.file, this.name);
        this.analyserIds = this.analysisProvider.getAnalyserIds();
        this.analysisExtension = new AnalysisExtension(this.fanChallenge, this.analyserIds);
    }

    @SuppressWarnings("unused")
    private static Set<IAnalyserId> getAnalyserIds()
    {
        return new HashSet<IAnalyserId>(Arrays.asList(BadAnalyserId.values()));
    }

    @Test
    public void testInstantingAnalysisExtension()
    {
        assertNotNull(this.analysisExtension);
        when(this.analysisProvider.getAnalysis()).thenReturn(BadAnalysis.INSTANCE);
    }
}
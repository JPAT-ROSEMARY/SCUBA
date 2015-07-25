package org.jpat.scuba.analysisprovider.statica.asm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.jpat.scuba.analysisprovider.statica.asm.command.system.ASMStaticAnalysisProviderCreator;
import org.jpat.scuba.analysisprovider.statica.asm.controller.analysis.InterfaceMethodInvokationStatsAdapter;
import org.jpat.scuba.core.command.system.PrepareBenchmarkCommand;
import org.jpat.scuba.core.command.system.ScubaControllerCreator;
import org.jpat.scuba.core.controller.ScubaController;
import org.jpat.scuba.core.controller.SimpleBytecodeProvider;
import org.jpat.scuba.core.controller.StatsProvider;
import org.jpat.scuba.core.model.AliasedScuba;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IAnalysisProvider;
import org.jpat.scuba.core.model.IScubaController;
import org.jpat.scuba.core.model.benchmark.BytecodeArtifactAnalysed;
import org.jpat.scuba.core.model.stats.BytecodeStats4All;
import org.junit.Before;
import org.junit.Test;

import de.schlichtherle.truezip.file.TFile;

public class InterfaceMethodInvokationStatsAdapterTest
{
    private IScubaController scubaController;
    private FANChallenge benchmarkModel;
    private InterfaceMethodInvokationStatsAdapter interfaceMethodInvkStatsAdapter;

    private static final int EXPECTED_VALUE_VISITED_TYPES = 3;
    private static final int EXPECTED_VALUE_VISITED_METHODS = 5; /** 4 concrete + 1 interface */
    private static final int EXPECTED_VALUE_ANALYSED_BYTECODE_INSTS = 61;
    private static final int EXPECTED_VALUE_INTERFACE_METHOD_INVK_COUNT = 1;

    private int actualValueAllTypesVisited;

    @Before
    public void setup()
    {
        final IAnalysisProvider analysisProvider = ASMStaticAnalysisProviderCreator.create();
        final List<IAnalysisProvider> analysisProviders = Arrays.asList(new IAnalysisProvider[] {analysisProvider });
        this.scubaController = (ScubaController) ScubaControllerCreator.create(analysisProviders);
        final TFile file = Constants.BENCHMARK_TEST_EXAMPLE_FILE;
        final String benchmarkExampleName = Constants.BENCHMARK_TEST_EXAMPLE;
        final String analysis = analysisProvider.getAnalysis().getTechnicalValue();
        this.benchmarkModel = ((ScubaController) this.scubaController).createBenchmark(analysis, file, benchmarkExampleName);
        new SimpleBytecodeProvider(this.scubaController).createBytecodeProviderExtension();
        new PrepareBenchmarkCommand(this.scubaController).run();

        this.actualValueAllTypesVisited = 0;
        this.interfaceMethodInvkStatsAdapter = new InterfaceMethodInvokationStatsAdapter(this.benchmarkModel);
    }

    @Test
    public void test()
    {
        this.interfaceMethodInvkStatsAdapter.run();
        final BytecodeStats4All bytecodeStats4All = this.benchmarkModel.getExtension(StatsProvider.class).getStats();
        final List<AliasedScuba> stats4All = bytecodeStats4All.getRealChildren();
        assertNotNull(stats4All);

        stats4All.forEach(t -> countAllValuesVisited((BytecodeArtifactAnalysed) t));

        assertEquals(EXPECTED_VALUE_VISITED_TYPES, this.actualValueAllTypesVisited);
        assertEquals(EXPECTED_VALUE_VISITED_METHODS, bytecodeStats4All.getVisitedMethodsStats());
        assertEquals(EXPECTED_VALUE_ANALYSED_BYTECODE_INSTS, bytecodeStats4All.getAnalysedBytecodeInstns());
        assertEquals(EXPECTED_VALUE_INTERFACE_METHOD_INVK_COUNT, bytecodeStats4All.getArtifactTargetedStats());
    }

    private void countAllValuesVisited(final BytecodeArtifactAnalysed bytecodeArtifactAnalysed)
    {
        if (bytecodeArtifactAnalysed.isitClassFile())
        {
            this.actualValueAllTypesVisited++;
        }
    }
}

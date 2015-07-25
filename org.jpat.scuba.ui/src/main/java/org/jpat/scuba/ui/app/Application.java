package org.jpat.scuba.ui.app;

import org.jpat.scuba.common.util.args.SimpleArgsValidator;
import org.jpat.scuba.core.model.FANChallenge;
import org.jpat.scuba.core.model.IScubaController;
import org.jpat.scuba.ui.commandhandler.InitialiseScuba;
import org.jpat.scuba.ui.commandhandler.PrepareBenchmark;
import org.jpat.scuba.ui.commandhandler.RunScuba;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application implements Runnable
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    private final IScubaController scubaController;

    public Application(final String benchmarkConfigFilePath)
    {
        assert null != benchmarkConfigFilePath && !benchmarkConfigFilePath.isEmpty() : "Parameter 'benchmarkConfigFilePath' of 'Application''s ctor must not be empty";

        ExtensionRegistry.createInstance();
        BenchmarkRegistry.createInstance(ExtensionRegistry.getInstance().getAnalysisProviderExtensions(), ExtensionRegistry.getInstance()
                .getConfigurationProviderExtension(), benchmarkConfigFilePath);
        this.scubaController = BenchmarkRegistry.getInstance().getController();
    }

    @Override
    public void run()
    {
        /** (1) */
        new InitialiseScuba().execute();

        /** (2) */
        /** Prepare benchmark bytecode artifacts input **/
        new PrepareBenchmark().execute();

        /** (3) */
        new RunScuba().execute();

        /** 'fanchallenge' is the main model and holds the analysis result, plus to the input model, which could be used for GUI as an input model
         * object value. */
        @SuppressWarnings("unused")
        final FANChallenge fanchallenge = this.scubaController.getFANChallenge();

        LOGGER.debug("I have run.");
        BenchmarkRegistry.delete();
    }

    /** 'args' is expected to pass a text file as an absolute path value. The file should contain pairs of key and value just like any '.properties'
     * file could be even without an extension. Check as an example "org.jpat.scuba.ui.benchmark.benchmarkResourceBundle"; On Development mode can
     * consume the benchmarking input configurations from ./src/main/resources properties file as follows
     * "./src/main/resources/org/jpat/scuba/ui/benchmark/benchmark.properties";
     * 
     * @param varargs
     *            bytecode archive file (Zip or Jar) to be analysed by SCUBA, of programs written in Java or Scala
     */
    public static void main(final String[] varargs)
    {
        String[] args4DevMode = varargs;
        if (null == args4DevMode || 0 == args4DevMode.length)
        {
            args4DevMode = new String[] {"./src/main/resources/org/jpat/scuba/ui/benchmark/benchmark.properties" };
        }
        final SimpleArgsValidator validateArgs4App = SimpleArgsValidator.getInstance(args4DevMode);
        validateArgs4App.runValidation();
        new Application(args4DevMode[0].trim()).run();
        LOGGER.debug("I am done.. Thanks!");
    }
}
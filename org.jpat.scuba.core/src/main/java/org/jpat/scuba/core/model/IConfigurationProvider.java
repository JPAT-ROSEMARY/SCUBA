package org.jpat.scuba.core.model;

public interface IConfigurationProvider extends IExtension
{
    String KEY_PROVIDER = "analysis.framework";
    String KEY_ANALYSIS_KIND = "analysis.kind";
    String KEY_BENCHMARK_NAME = "benchmarkExample.name";
    String KEY_LANGUAGE = "language";
    String KEY_BINARY_ROOT_PATH = "binary.root.path";
    String KEY_EXTERNAL_TREATMENT = "external";

    /**
     * @return
     *      Analysis Provider (Framework Library) name; e.g., ASM, BCEL, and etcetera
     */
    String analysisProvider();

    /**
     * @return
     *      Kind of target analysis; e.g., Static, Instrumentation, and etc
     */
    String analysisKind();

    /**
     * @return
     *      Benchmark example program name
     */
    String benchmarkExampleName();

    /**
     * @return
     *      The programming language the subject program written with
     */
    String language();

    /**
     * @return
     *      Directory where the bytecode files is located either as archive like JAR, ZIP, and etc or set of .class files
     */
    String bytecodePlacePath();

    /**
     * @return
     *      Excluded when should not consider analysing external jars/binaries;
     *      Included otherwise.
     */
    String externalTreatment();

    boolean isReady();
}

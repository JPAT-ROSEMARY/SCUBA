package org.jpat.scuba.ui.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jpat.scuba.core.benchmark.configuration.IConfigurationProviderExtension;
import org.jpat.scuba.core.command.system.BytecodeProviderCreator;
import org.jpat.scuba.core.command.system.IAnalysisProviderExtension;
import org.jpat.scuba.core.command.system.ScubaControllerCreator;
import org.jpat.scuba.core.model.IAnalysisProvider;
import org.jpat.scuba.core.model.IBytecodeProvider;
import org.jpat.scuba.core.model.IConfigurationProvider;
import org.jpat.scuba.core.model.IScubaController;
import org.jpat.scuba.foundation.exceptions.ConfigurationProviderNotRegisteredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BenchmarkRegistry
{
    private static final Logger LOGGER = LoggerFactory.getLogger(BenchmarkRegistry.class);

    private static BenchmarkRegistry instance;

    private final List<IAnalysisProvider> analysisProviders;
    private final List<IConfigurationProviderExtension> configurationProviderExtensions;
    private final String benchmarkPreferencesFilePath;
    private IScubaController controller;
    private IConfigurationProvider benchmarkConfigurationProvider;
    private IBytecodeProvider bytecodeProvider;

    private BenchmarkRegistry(final List<IAnalysisProviderExtension> analysisProviderExtensions,
            final List<IConfigurationProviderExtension> configurationProviderExtension, final String benchmarkPreferencesFilePath)
    {
        assert null != analysisProviderExtensions : "Parameter 'analysisProviderExtensions' of 'BenchmarkRegistry''s ctor must not be null";
        assert null != configurationProviderExtension : "Parameter 'configurationProviderExtension' of 'BenchmarkRegistry''s ctor must not be null";
        assert null != benchmarkPreferencesFilePath && !benchmarkPreferencesFilePath.isEmpty() : "Parameter 'configurationFilePath' of 'BenchmarkRegistry''s ctor must not be empty";

        this.analysisProviders = new ArrayList<>(analysisProviderExtensions.size());

        final StringBuilder strBldr = new StringBuilder();

        final int length = analysisProviderExtensions.size();
        if (0 >= length)
        {
            strBldr.append("\n\n\t[*] System has not found any configured Analysis engine!");
        }
        else
        {
            strBldr.append("\n\n\t[*] Pre-configured Analysis engines: ");
            analysisProviderExtensions.forEach(analysisProviderExtension -> {
                strBldr.append("\n\t(");
                strBldr.append(incrementAnalysisConfiguredCounter());
                strBldr.append(") '");
                strBldr.append(analysisProviderExtension.getAnalysisProvider().getAnalysis());
                strBldr.append("'");
                this.analysisProviders.add(analysisProviderExtension.getAnalysisProvider());
            });
        }
        strBldr.append('\n');
        LOGGER.info(strBldr.toString());
        this.benchmarkPreferencesFilePath = benchmarkPreferencesFilePath;
        this.configurationProviderExtensions = configurationProviderExtension;
    }

    private static int analysisConfiguredCounter = 1;

    private static String incrementAnalysisConfiguredCounter()
    {
        return String.valueOf(analysisConfiguredCounter++);
    }

    public static void createInstance(final List<IAnalysisProviderExtension> analysisProviderExtensions,
            final List<IConfigurationProviderExtension> configurationProviderExtension, final String configurationFilePath)
    {
        assert !hasInstance() : "Instance alreay created";

        instance = new BenchmarkRegistry(analysisProviderExtensions, configurationProviderExtension, configurationFilePath);
    }

    private static boolean hasInstance()
    {
        return null != instance;
    }

    public static BenchmarkRegistry getInstance()
    {
        assert hasInstance() : "Instance not created";

        return instance;
    }

    private void deleteInternal()
    {
        assert hasInstance() : "Instance has not been created";

        this.controller = null;
        instance = null;
    }

    public static void delete()
    {
        BenchmarkRegistry.getInstance().deleteInternal();
    }

    public IScubaController getController()
    {
        if (null == this.controller)
        {
            this.controller = ScubaControllerCreator.create(this.analysisProviders);
        }
        return this.controller;
    }

    public List<IAnalysisProvider> getAvailableAnalysisProviders()
    {
        return Collections.unmodifiableList(this.analysisProviders);
    }

    public IConfigurationProvider getConfigurationProvider()
    {
        if (null == this.benchmarkConfigurationProvider)
        {
            for (final IConfigurationProviderExtension nextConfigurationProviderExtension : this.configurationProviderExtensions)
            {
                this.benchmarkConfigurationProvider = nextConfigurationProviderExtension.getConfigurationProvider(this.benchmarkPreferencesFilePath);
                if (null != this.benchmarkConfigurationProvider && this.benchmarkConfigurationProvider.isReady())
                {
                    break;
                }
            }
        }
        if (null == this.benchmarkConfigurationProvider)
        {
            throw new ConfigurationProviderNotRegisteredException();
        }
        return this.benchmarkConfigurationProvider;
    }

    public void instantiateBytecodeProvider()
    {
        if (null == this.bytecodeProvider)
        {
            this.bytecodeProvider = BytecodeProviderCreator.instantiate(this.controller);
        }
    }
}
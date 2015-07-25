package org.jpat.scuba.ui.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.jpat.scuba.core.benchmark.configuration.IConfigurationProviderExtension;
import org.jpat.scuba.core.benchmark.configuration.IConfigurationValue;
import org.jpat.scuba.core.command.system.IAnalysisProviderExtension;
import org.jpat.scuba.core.platform.DefaultConfigurationValue;
import org.jpat.scuba.core.platform.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class ExtensionRegistry
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ExtensionRegistry.class);

    private static final String ANALYSIS_PROVIDER_EXTENSION_ID = "org.jpat.scuba.analysisprovider.extension";
    private static final String CONFIGURATION_PROVIDER_EXTENSION_ID = "org.jpat.scuba.configurationsprovider.extensions";

    private static ExtensionRegistry instance;
    private List<IAnalysisProviderExtension> analysisProviderExtensions;
    private List<IConfigurationProviderExtension> configurationProviderExtensions;

    private ExtensionRegistry()
    {
        super();
    }

    public static void createInstance()
    {
        assert !hasInstance() : "Instance has been already created";

        instance = new ExtensionRegistry();
    }

    private static boolean hasInstance()
    {
        return null != instance;
    }

    public static ExtensionRegistry getInstance()
    {
        assert hasInstance() : "Instance has not been created";

        return instance;
    }

    public List<IConfigurationProviderExtension> getConfigurationProviderExtension()
    {
        if (null == this.configurationProviderExtensions)
        {
            Platform.getExtensionRegistry().addConfigurationItem(CONFIGURATION_PROVIDER_EXTENSION_ID,
                    new DefaultConfigurationValue("org.jpat.scuba.core.benchmark.configuration.ConfigurationProviderExtension"));

            Platform.getExtensionRegistry().addConfigurationItem(CONFIGURATION_PROVIDER_EXTENSION_ID,
                    new DefaultConfigurationValue("org.jpat.scuba.configurationsprovider.extensions.ApacheConfigurationProviderExtension"));
            final IConfigurationValue[] configurationItems = Platform.getExtensionRegistry().getConfigurationItem4(
                    CONFIGURATION_PROVIDER_EXTENSION_ID);

            if (null == configurationItems)
            {
                return null;
            }

            this.configurationProviderExtensions = new ArrayList<>(2);
            Arrays.asList(configurationItems).forEach(nextConfItem -> {
                final Object extension = nextConfItem.createExecutableExtension();
                if (extension instanceof IConfigurationProviderExtension)
                {
                    this.configurationProviderExtensions.add((IConfigurationProviderExtension) extension);
                }
            });
        }
        return this.configurationProviderExtensions;
    }

    public List<IAnalysisProviderExtension> getAnalysisProviderExtensions()
    {
        if (null != this.analysisProviderExtensions && !this.analysisProviderExtensions.isEmpty())
        {
            return Collections.unmodifiableList(this.analysisProviderExtensions);
        }
        Platform.getExtensionRegistry().addConfigurationItem(ANALYSIS_PROVIDER_EXTENSION_ID,

        new DefaultConfigurationValue("org.jpat.scuba.system.ASMStaticAnalysisProviderExtension"));
        Platform.getExtensionRegistry().addConfigurationItem(ANALYSIS_PROVIDER_EXTENSION_ID,
                new DefaultConfigurationValue("org.jpat.scuba.system.ASMScalaStaticAnalysisProviderExtension"));
        Platform.getExtensionRegistry().addConfigurationItem(ANALYSIS_PROVIDER_EXTENSION_ID,
                new DefaultConfigurationValue("org.jpat.scuba.system.BCELStaticAnalysisProviderExtension"));
        Platform.getExtensionRegistry().addConfigurationItem(ANALYSIS_PROVIDER_EXTENSION_ID,
                new DefaultConfigurationValue("org.jpat.scuba.system.ASMDynamicAnalysisProviderExtension"));
        Platform.getExtensionRegistry().addConfigurationItem(ANALYSIS_PROVIDER_EXTENSION_ID,
                new DefaultConfigurationValue("org.jpat.scuba.system.BCELDynamicAnalysisProviderExtension"));

        final IConfigurationValue[] configurationItems = Platform.getExtensionRegistry().getConfigurationItem4(ANALYSIS_PROVIDER_EXTENSION_ID);

        if (null == configurationItems)
        {
            return Collections.emptyList();
        }

        this.analysisProviderExtensions = new ArrayList<>(4);
        Arrays.asList(configurationItems).forEach(nextConfItem -> {
            final Object extension = nextConfItem.createExecutableExtension();
            if (extension instanceof IAnalysisProviderExtension)
            {
                final IAnalysisProviderExtension analysisProviderExtension = (IAnalysisProviderExtension) extension;
                this.analysisProviderExtensions.add(analysisProviderExtension);
            }
        });

        if (null == this.analysisProviderExtensions || 1 > this.analysisProviderExtensions.size())
        {
            LOGGER.error("Platform has not been able to register any analysis provider extension!");
        }
        return Collections.unmodifiableList(this.analysisProviderExtensions);
    }

    public void delete()
    {
        assert hasInstance() : "Instance has not been created";

        this.analysisProviderExtensions = null;
        this.configurationProviderExtensions = null;
        instance = null;
    }
}

package org.jpat.scuba.core.model;

import java.util.ArrayList;
import java.util.List;

import org.jpat.scuba.common.util.IPathResolver;
import org.jpat.scuba.core.controller.StatsProvider;
import org.jpat.scuba.core.foundation.Analysis;
import org.jpat.scuba.core.foundation.Language;
import org.jpat.scuba.core.model.benchmark.BenchmarkBytecodeContainer;
import org.jpat.scuba.core.model.benchmark.BytecodeArtifact;
import org.jpat.scuba.core.model.benchmark.BytecodeArtifactAnalysed;
import org.jpat.scuba.core.model.stats.BytecodeStats;

import de.schlichtherle.truezip.file.TFile;

public class FANChallenge extends AliasedScubaContainer
{
    private static final String DEFAULT = "Default";

    protected final List<IExtension> extensions = new ArrayList<>();

    private String name;
    private Language language;
    private Analysis analysis;
    private final String technicalInternalName;

    public FANChallenge(final TFile file, final String name)
    {
        this(file, name, DEFAULT);
    }

    public FANChallenge(final TFile file, final String name, final String technicaInternalName)
    {
        assert null != name && !name.isEmpty() : "Parameter 'name' of 'FANChallenge''s ctor must not be empty";
        assert null != technicaInternalName && !technicaInternalName.isEmpty() : "Parameter 'technicaInternalName' of 'FANChallenge''s ctor must not be empty";

        this.name = name;
        this.technicalInternalName = technicaInternalName;
        addChild(new Artifacts(this, name, file));
        addChild(new Analysers(this));
    }

    @Override
    public String getAlias()
    {
        return this.name;
    }

    public final void addExtension(final IExtension extension)
    {
        assert null != extension : "Parameter 'extension' of method 'addExtension' must not be null";
        assert !this.extensions.contains(extension) : "extension already added: " + extension;

        this.extensions.add(extension);
    }

    public final Language getLanguage()
    {
        return this.language;
    }

    public final Analysis getAnalysis()
    {
        return this.analysis;
    }

    public final void setLanguage(final Language language)
    {
        assert null != language : "Parameter 'language' of method 'setLanguage' must not be null";

        this.language = language;
    }

    public final void setAnalysis(final Analysis analysis)
    {
        assert null != analysis : "Parameter 'analysis' of method 'setAnalysis' must not be null";

        this.analysis = analysis;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final <T extends IExtension> T getExtension(final Class<T> extensionClass)
    {
        assert null != extensionClass : "Parameter 'extensionClass' of method 'getExtension' must not be null";

        for (final IExtension nextExtension : this.extensions)
        {
            if (extensionClass.isAssignableFrom(nextExtension.getClass()))
            {
                return (T) nextExtension;
            }
        }
        assert false : "Extension not found: " + extensionClass.getName();

        return null;
    }

    public final Artifacts getArtifacts()
    {
        final Artifacts artifacts = getChild(Artifacts.class);

        assert null != artifacts : "'artifacts' in method 'getArtifacts' must not be null";

        return artifacts;
    }

    public final List<BytecodeArtifact> getBytecodeArtifacts()
    {
        return ((AliasedScuba) this.getBytecodeContainer()).getChildren(BytecodeArtifact.class);
    }

    public final IBytecodeContainer getBytecodeContainer()
    {
        return getArtifacts().getChild(BenchmarkBytecodeContainer.class);
    }

    public final String getBytecodeContainerPath()
    {
        return ((BenchmarkBytecodeContainer) this.getBytecodeContainer()).getBytecodeArtifactPath();
    }

    public final List<BytecodeArtifactAnalysed> getBytecodeArtifactsAnalysed()
    {
        return getExtension(StatsProvider.class).getStats().getChildren(BytecodeArtifactAnalysed.class);
    }

    public final Analysers getAnalysers()
    {
        final Analysers analysers = getChild(Analysers.class);

        assert null != analysers : "Variable 'analysers' in method 'getAnalysers' must not be null";

        return analysers;
    }

    public final void addBytecodeArtificatAnalysed(final String classQualifiedName, final byte[] bytes, final String absolutePath,
            final IPathResolver directoryResolver, final boolean isClassFile, final boolean isInterface)
    {
        final BytecodeStats stats4SingleType = this.getExtension(IStatsProvider.class).getBytecodeStats();

        assert null != stats4SingleType : "'stats4SingleType' in method 'addBytecodeArtificatAnalysed' must not be null";

        new BytecodeArtifactAnalysed(this, classQualifiedName, bytes, stats4SingleType, absolutePath, directoryResolver, isClassFile, isInterface);
    }

    @Override
    public final String toString()
    {
        return this.technicalInternalName;
    }
}

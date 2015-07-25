package org.jpat.scuba.core.controller;

import org.jpat.scuba.common.util.IPathResolver;
import org.jpat.scuba.core.benchmark.archive.SimpleArchiveExtractor;
import org.jpat.scuba.core.controller.benchmark.BytecodeProviderExtension;
import org.jpat.scuba.core.model.IBytecodeContainer;
import org.jpat.scuba.core.model.IBytecodeProvider;
import org.jpat.scuba.core.model.IScubaController;
import org.jpat.scuba.core.model.benchmark.IArchiveInOut;
import org.jpat.scuba.foundation.exceptions.NotArchiveException;

import de.schlichtherle.truezip.file.TFile;

public abstract class AbstractBytecodeProvider implements IBytecodeProvider
{
    private final IArchiveInOut archiveExtractor;

    protected AbstractBytecodeProvider(final IScubaController controller)
    {
        assert null != controller : "Parameter 'controller' of 'AbstractBytecodeProvider''s ctor must not be null";

        final IBytecodeContainer container = controller.getBytecodeContainer();

        assert null != container : "'container' in method 'AbstractBytecodeProvider' must not be null";

        final TFile benchmarkBytecodeArchive = controller.getBenchmarkExampleBytecodePlace();
        if (!benchmarkBytecodeArchive.isArchive())
        {
            throw new NotArchiveException(benchmarkBytecodeArchive);
        }
        final IPathResolver pathResolver = container.getPathResolver();
        this.archiveExtractor = new SimpleArchiveExtractor(container, pathResolver, benchmarkBytecodeArchive);
        controller.instantiateBytecodeProvider(this);
    }

    @Override
    public BytecodeProviderExtension createBytecodeProviderExtension()
    {
        return new BytecodeProviderExtension(this.archiveExtractor);
    }
}

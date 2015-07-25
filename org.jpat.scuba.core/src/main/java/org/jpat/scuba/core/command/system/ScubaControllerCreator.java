package org.jpat.scuba.core.command.system;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jpat.scuba.core.controller.AnalysisProvider;
import org.jpat.scuba.core.controller.ScubaController;
import org.jpat.scuba.core.model.IAnalysisProvider;
import org.jpat.scuba.core.model.IScubaController;

public final class ScubaControllerCreator
{
    public ScubaControllerCreator()
    {
        super();
    }

    public static IScubaController create(final List<IAnalysisProvider> analysisProviders)
    {
        assert null != analysisProviders : "Parameter 'analysisProviders' of method 'create' must not be null";
        assert !analysisProviders.isEmpty() : "Parameter 'analysisProviders' of method 'create' must not be empty";

        final Set<AnalysisProvider> concreteAnalysisProviders = new HashSet<>(analysisProviders.size());
        analysisProviders.forEach(nextAnalysisProvider -> {
            assert nextAnalysisProvider instanceof AnalysisProvider : "Not an analysis provider: '" + nextAnalysisProvider.getClass() + "'";

            concreteAnalysisProviders.add((AnalysisProvider) nextAnalysisProvider);
        });
        return new ScubaController(concreteAnalysisProviders);
    }
}

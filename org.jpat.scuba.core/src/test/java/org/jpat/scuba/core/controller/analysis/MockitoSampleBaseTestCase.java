package org.jpat.scuba.core.controller.analysis;

import org.jpat.scuba.core.controller.SimpleBaseTestCase;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

public abstract class MockitoSampleBaseTestCase extends SimpleBaseTestCase
{
    @Before
    public void initMocks()
    {
        MockitoAnnotations.initMocks(this);
    }
}

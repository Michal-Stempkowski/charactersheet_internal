package com.github.michal_stempkowski.charactersheet.internal.parallelism;

import com.github.michal_stempkowski.charactersheet.internal.app.AppRootLogic;
import com.github.michal_stempkowski.charactersheet.internal.app.TopLogicFactory;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ParallelismDomainTest {
    @Test
    public void onInitializationShouldCreateTaskScheduler() {
        // Given:
        TopLogicFactory topLogicMock = mock(TopLogicFactory.class);
        AppRootLogic root = new AppRootLogic(topLogicMock);
        ParallelismDomain uut = new ParallelismDomain();
        // When:
        uut.setup();
        // Then:
        verify(topLogicMock).getTaskScheduler();
    }
}
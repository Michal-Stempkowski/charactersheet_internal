package com.github.michal_stempkowski.charactersheet.internal.parallelism;

import com.github.michal_stempkowski.charactersheet.internal.app.AppRootLogic;
import com.github.michal_stempkowski.charactersheet.internal.app.TopLogicFactory;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ParallelismDomainTest {
    @Test
    public void onInitializationShouldCreateTaskScheduler() {
        // Given:
        TopLogicFactory topLogicMock = mock(TopLogicFactory.class);
        @SuppressWarnings("unused") AppRootLogic root = new AppRootLogic(topLogicMock);
        ParallelismDomain uut = new ParallelismDomain();
        // When:
        uut.setup();
        // Then:
        verify(topLogicMock).getTaskScheduler();
        assertThat(uut.getStatus().hasErrorOccurred(), is(false));
    }
}
package com.github.michal_stempkowski.charactersheet.internal.parallelism;

import com.github.michal_stempkowski.charactersheet.internal.app.AppRootLogic;
import com.github.michal_stempkowski.charactersheet.internal.app.TopLogicFactory;
import com.github.michal_stempkowski.charactersheet.internal.events.EventDispatcher;
import com.github.michal_stempkowski.charactersheet.internal.events.events.InitializeEvent;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ParallelismDomainTest {
    @Test
    public void onInitializationShouldCreateTaskScheduler() {
        // Given:
        TopLogicFactory topLogicMock = mock(TopLogicFactory.class);
        EventDispatcher eventDispatcherMock = mock(EventDispatcher.class);
        @SuppressWarnings("unused") AppRootLogic root = new AppRootLogic(topLogicMock);
        ParallelismDomain uut = new ParallelismDomain();
        when(topLogicMock.getEventDispatcher()).thenReturn(eventDispatcherMock);
        // When:
        uut.setup();
        // Then:
        verify(topLogicMock).getTaskScheduler();
        verify(eventDispatcherMock).registerListener(eq(InitializeEvent.eventType()), any());
        assertThat(uut.getStatus().hasErrorOccurred(), is(false));
    }
}
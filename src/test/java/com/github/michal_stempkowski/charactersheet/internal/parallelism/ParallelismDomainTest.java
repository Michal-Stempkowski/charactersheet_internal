package com.github.michal_stempkowski.charactersheet.internal.parallelism;

import com.github.michal_stempkowski.charactersheet.internal.app.AppRootLogic;
import com.github.michal_stempkowski.charactersheet.internal.app.TopLogicFactory;
import com.github.michal_stempkowski.charactersheet.internal.events.Event;
import com.github.michal_stempkowski.charactersheet.internal.events.EventDispatcher;
import com.github.michal_stempkowski.charactersheet.internal.events.events.InitializeEvent;
import com.github.michal_stempkowski.charactersheet.internal.events.events.ShutdownPerformedEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.time.Duration;
import java.util.function.Consumer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ParallelismDomainTest {

    private EventDispatcher eventDispatcherMock;
    private TaskScheduler taskSchedulerMock;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private AppRootLogic root;
    private ParallelismDomain uut;
    private TopLogicFactory topLogicMock;

    @Before
    public void setUp() {
        topLogicMock = mock(TopLogicFactory.class);
        eventDispatcherMock = mock(EventDispatcher.class);
        taskSchedulerMock = mock(TaskScheduler.class);
        root = new AppRootLogic(topLogicMock);
        uut = new ParallelismDomain();
        when(topLogicMock.getEventDispatcher()).thenReturn(eventDispatcherMock);
        when(topLogicMock.getTaskScheduler()).thenReturn(taskSchedulerMock);
    }

    @Test
    public void onInitializationShouldCreateTaskScheduler() {
        // When:
        uut.setup();
        // Then:
        verify(topLogicMock).getTaskScheduler();
        verify(eventDispatcherMock).registerListener(eq(InitializeEvent.eventType()), any());
        verify(eventDispatcherMock).registerListener(eq(ShutdownPerformedEvent.eventType()), any());
        assertThat(uut.getStatus().hasErrorOccurred(), is(false));
    }

    @Test
    public void onInitializeEventTaskSchedulerShouldBeInitialized() {
        // Given:
        ArgumentCaptor<Consumer<Event>> initializeEventMethodCaptor = ArgumentCaptor.forClass((Class)Consumer.class);
        uut.setup();
        verify(topLogicMock).getTaskScheduler();
        verify(eventDispatcherMock).registerListener(
                eq(InitializeEvent.eventType()), initializeEventMethodCaptor.capture());
        verify(eventDispatcherMock).registerListener(eq(ShutdownPerformedEvent.eventType()), any());
        // When:
        initializeEventMethodCaptor.getValue().accept(new InitializeEvent());
        // Then:
        verify(taskSchedulerMock).init();
    }

    @Test
    public void onShutdownPerformedEventTaskSchedulerShouldBeGentleShutdown() {
        // Given:
        ArgumentCaptor<Consumer<Event>> initializeEventMethodCaptor = ArgumentCaptor.forClass((Class)Consumer.class);
        uut.setup();
        verify(topLogicMock).getTaskScheduler();
        verify(eventDispatcherMock).registerListener(eq(InitializeEvent.eventType()), any());
        verify(eventDispatcherMock).registerListener(
                eq(ShutdownPerformedEvent.eventType()), initializeEventMethodCaptor.capture());
        // When:
        initializeEventMethodCaptor.getValue().accept(new ShutdownPerformedEvent());
        // Then:
        verify(taskSchedulerMock).gentleShutdown(any(Duration.class), any(Duration.class));
    }
}
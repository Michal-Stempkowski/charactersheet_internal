package com.github.michal_stempkowski.charactersheet.internal.app;

import com.github.michal_stempkowski.charactersheet.internal.InternalDomainId;
import com.github.michal_stempkowski.charactersheet.internal.Target;
import com.github.michal_stempkowski.charactersheet.internal.events.EventDispatcher;
import com.github.michal_stempkowski.charactersheet.internal.utils.ErrorMonad;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.*;

public class AppRootLogicTest {
    private TopLogicFactory topLogicFactoryMock;
    private List<Domain> domainMocks;
    private List<PackageInitializer> packageInitializerMocks;
    private Domain domainFailedInitialization;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private AppRootLogic sut;
    private EventDispatcher eventDispatcherMock;
    private Logger loggerMock;

    @Before
    public void setUp() {
        topLogicFactoryMock = mock(TopLogicFactory.class);
        setupPackageInitializerMocks();
        setupDomainMocks();

        eventDispatcherMock = mock(EventDispatcher.class);
        loggerMock = mock(Logger.class);

        sut = new AppRootLogic(topLogicFactoryMock);
    }

    private void setupDomainMocks() {
        domainMocks = new LinkedList<>();
        domainMocks.add(mock(Domain.class));

        domainMocks.forEach(x -> when(x.getStatus()).thenReturn(new ErrorMonad()));

        domainFailedInitialization = mock(Domain.class);
        when(domainFailedInitialization.getStatus()).thenReturn(new ErrorMonad(new Exception()));
    }

    private void setupPackageInitializerMocks() {
        packageInitializerMocks = new LinkedList<>();
        packageInitializerMocks.add(mock(PackageInitializer.class));
    }

    private void wirePackageInitializersAndDomains() {
        when(topLogicFactoryMock.getPackageInitializers()).thenReturn(packageInitializerMocks);
        when(packageInitializerMocks.get(0).init()).thenReturn(domainMocks);
    }

    @Test
    public void onNoPackageInitializerProvidedInitShouldReturnNoDomain() {
        // Given:
        when(topLogicFactoryMock.getPackageInitializers()).thenReturn(new LinkedList<>());
        // When:
        boolean initializationSucceded = AppRootLogic.init();
        // Then:
        assertThat(initializationSucceded, is(true));
    }

    @Test
    public void onValidPackageInitializationInitShouldReturnTrue() {
        // Given:
        wirePackageInitializersAndDomains();
        // When:
        boolean initializationSucceded = AppRootLogic.init();
        // Then:
        assertThat(initializationSucceded, is(true));
        assertThatAllInitialized();
    }

    @Test
    public void onInvalidPackageInitializationInitShouldReturnFalse() {
        // Given:
        wirePackageInitializersAndDomains();
        domainMocks.add(domainFailedInitialization);
        // When:
        boolean initializationSucceed = AppRootLogic.init();
        // Then:
        assertThat(initializationSucceed, is(false));
        assertThatAllInitialized();
    }

    @Test
    public void shouldProvideWithEventDispatcher() {
        // Given:
        when(topLogicFactoryMock.getEventDispatcher()).thenReturn(eventDispatcherMock);
        // When/Then:
        assertThat(AppRootLogic.getEventDispatcher(), is(equalTo(eventDispatcherMock)));
        verify(topLogicFactoryMock).getEventDispatcher();
    }

    @Test
    public void shouldBeAbleToCreateLogger() {
        // Given:
        when(topLogicFactoryMock.createLogger(Target.INTERNAL, InternalDomainId.LOGGING, getClass().getName()))
                .thenReturn(loggerMock);
        // When/Then:
        assertThat(AppRootLogic.createLogger(Target.INTERNAL, InternalDomainId.LOGGING, getClass().getName()),
                is(equalTo(loggerMock)));
        verify(topLogicFactoryMock).createLogger(Target.INTERNAL, InternalDomainId.LOGGING, getClass().getName());
    }

    @Test
    public void shouldBeAbleToStartApplicationAndAwaitItsTermination() {
        // When:
        AppRootLogic.start();
        // Then:
        verify(topLogicFactoryMock).start();
    }

    private void assertThatAllInitialized() {
        verify(topLogicFactoryMock).getPackageInitializers();
        packageInitializerMocks.forEach(x -> verify(x).init());
    }

    @Test
    public void shouldBeAbleToScheduleLongRunningTasks() {
        // When:
        AppRootLogic.getTaskScheduler();
        // Then:
        verify(topLogicFactoryMock).getTaskScheduler();
    }
}
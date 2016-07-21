package com.github.michal_stempkowski.charactersheet.internal.events;

import com.github.michal_stempkowski.charactersheet.internal.app.AppRootLogic;
import com.github.michal_stempkowski.charactersheet.internal.app.Domain;
import com.github.michal_stempkowski.charactersheet.internal.app.TopLogicFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EventDomainTest {

    private TopLogicFactory topLogicFactoryMock;
    private Domain uut;

    @Before
    public void setUp() {
        topLogicFactoryMock = mock(TopLogicFactory.class);
        EventDispatcher  eventDispatcherMock = mock(EventDispatcher.class);
        @SuppressWarnings("unused") AppRootLogic root = new AppRootLogic(topLogicFactoryMock);
        when(topLogicFactoryMock.getEventDispatcher()).thenReturn(eventDispatcherMock);
        uut = new EventDomain();
    }

    @Test
    public void domainCreationShouldWork() {
       // When:
        uut.setup();
        // Then:
        verify(topLogicFactoryMock).getEventDispatcher();
        assertThat(uut.getStatus().hasErrorOccurred(), is(false));
    }
}
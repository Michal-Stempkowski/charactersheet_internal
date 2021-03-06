package com.github.michal_stempkowski.charactersheet.internal.logging;

import com.github.michal_stempkowski.charactersheet.internal.InternalDomainId;
import com.github.michal_stempkowski.charactersheet.internal.Target;
import com.github.michal_stempkowski.charactersheet.internal.app.AppRootLogic;
import com.github.michal_stempkowski.charactersheet.internal.app.Domain;
import com.github.michal_stempkowski.charactersheet.internal.app.TopLogicFactory;
import com.github.michal_stempkowski.charactersheet.internal.DomainId;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class LoggingDomainTest {
    private TopLogicFactory topLogicFactoryMock;
    private Domain uut;

    @Before
    public void setUp() {
        topLogicFactoryMock = mock(TopLogicFactory.class);
        Logger loggerMock = mock(Logger.class);
        @SuppressWarnings("unused") AppRootLogic root = new AppRootLogic(topLogicFactoryMock);
        when(topLogicFactoryMock.createLogger(
                any(Target.class), any(DomainId.class), any(String.class))).thenReturn(loggerMock);
        uut = new LoggingDomain();
    }

    @Test
    public void domainCreationShouldWork() {
        // When:
        uut.setup();
        // Then:
        verify(topLogicFactoryMock).createLogger(Target.INTERNAL, InternalDomainId.LOGGING, uut.getClass().getName());
        assertThat(uut.getStatus().hasErrorOccurred(), is(false));
    }
}
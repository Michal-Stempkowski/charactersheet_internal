package com.github.michal_stempkowski.charactersheet.internal.app;

import com.github.michal_stempkowski.charactersheet.internal.utils.ErrorMonad;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.Mockito.*;

public class PackageInitializerTest {

    private PackageInitializer sut;
    private Domain domainMock;
    private ArrayList<Supplier<Domain>> suppliers;

    @Before
    public void setUp() {
        domainMock = mock(Domain.class);
        suppliers = new ArrayList<>(Collections.singletonList(() -> domainMock));

        sut = new PackageInitializer() {
            @Override
            protected List<Supplier<Domain>> getDomainCreators() {
                return suppliers;
            }
        };
    }

    @Test
    public void shouldInitAlDomains() {
        // When:
        List<Domain> domains = sut.init();
        // Then:
        assertThat(domains.size(), is(equalTo(1)));
        assertThat(domains, hasItem(domainMock));
        verify(domainMock).setup();
    }

    @Test
    public void domainStateShouldBeIrrelevant() {
        // Given:
        Domain anotherDomain = mock(Domain.class);
        when(anotherDomain.getStatus()).thenReturn(new ErrorMonad(new Exception()));
        suppliers.add(0, () -> anotherDomain);
        // When:
        List<Domain> domains = sut.init();
        // Then:
        assertThat(domains.size(), is(equalTo(2)));

        assertDomainInitializedButStatusNotQueried(domains, domainMock);
        assertDomainInitializedButStatusNotQueried(domains, anotherDomain);

        assertThat(anotherDomain.getStatus().hasErrorOccurred(), is(true));
    }

    private void assertDomainInitializedButStatusNotQueried(List<Domain> domains, Domain domainMock) {
        assertThat(domains, hasItem(domainMock));
        verify(domainMock).setup();
        verify(domainMock, never()).getStatus();
    }

}
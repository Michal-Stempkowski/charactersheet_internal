package com.github.michal_stempkowski.charactersheet.internal.events;

import com.github.michal_stempkowski.charactersheet.internal.Target;
import com.github.michal_stempkowski.charactersheet.internal.events.events.InitializeEvent;
import com.github.michal_stempkowski.charactersheet.internal.events.events.ShutdownPerformedEvent;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.fail;

public class EventTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void calculateEventTypeShouldThrowOnInvalidNumbers() {
        assertCalculationFailed(-1, 0, 0, -1);
        assertCalculationFailed(0, -1, 0, -1);
        assertCalculationFailed(0, Target.Consts.DOMAINS_ALLOWED, 0, Target.Consts.DOMAINS_ALLOWED);
        assertCalculationFailed(0, 0, -1, -1);
        assertCalculationFailed(0, 0, Target.Consts.EVENTS_IN_DOMAIN_ALLOWED, Target.Consts.EVENTS_IN_DOMAIN_ALLOWED);

        assertThat(Event.calculateEventType(1, 1, 15), is(equalTo(1_049_615)));
    }

    private void assertCalculationFailed(int targetId, int domainId, int eventId, int expectedErrorValue) {
        try {
            Event.calculateEventType(targetId, domainId, eventId);
            fail();
        } catch (Event.EventTypeCalculationError ex) {
            assertThat(ex.errorValue, is(equalTo(expectedErrorValue)));
        }
    }

    @Test
    public void shouldWarnAboutInvalidEventCast() {
        expectedException.expect(ClassCastException.class);
        InitializeEvent ev = Event.tryCast(new ShutdownPerformedEvent());
    }

    @Test
    public void eventTypeShouldBeAPlaceholderMethod() {
        expectedException.expect(UnsupportedOperationException.class);
        Event.eventType();
    }
}
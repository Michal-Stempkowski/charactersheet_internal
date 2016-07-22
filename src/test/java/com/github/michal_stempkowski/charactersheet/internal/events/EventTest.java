package com.github.michal_stempkowski.charactersheet.internal.events;

import com.github.michal_stempkowski.charactersheet.internal.DomainId;
import com.github.michal_stempkowski.charactersheet.internal.EventId;
import com.github.michal_stempkowski.charactersheet.internal.InternalDomainId;
import com.github.michal_stempkowski.charactersheet.internal.Target;
import com.github.michal_stempkowski.charactersheet.internal.events.events.InitializeEvent;
import com.github.michal_stempkowski.charactersheet.internal.events.events.ShutdownPerformedEvent;
import com.github.michal_stempkowski.charactersheet.internal.parallelism.ParallelismEventId;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.fail;

class DummyHolder implements DomainId, EventId {
    private final int value;

    public DummyHolder(int val) {
        value = val;
    }

    @Override
    public int id() {
        return value;
    }

    @Override
    public String name() {
        return null;
    }
}

public class EventTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void calculateEventTypeShouldThrowOnInvalidNumbers() {
        assertCalculationFailed(Target.UNDEFINED, new DummyHolder(0), new DummyHolder(0), -1);
        assertCalculationFailed(Target.INTERNAL, new DummyHolder(-1), new DummyHolder(0), -1);
        assertCalculationFailed(
                Target.INTERNAL, new DummyHolder(Target.Consts.DOMAINS_ALLOWED), new DummyHolder(0),
                Target.Consts.DOMAINS_ALLOWED);
        assertCalculationFailed(
                Target.INTERNAL, new DummyHolder(0), new DummyHolder(-1), -1);
        assertCalculationFailed(
                Target.INTERNAL, new DummyHolder(0), new DummyHolder(Target.Consts.EVENTS_IN_DOMAIN_ALLOWED),
                Target.Consts.EVENTS_IN_DOMAIN_ALLOWED);

        assertThat(
                Event.calculateEventType(
                        Target.INTERNAL, InternalDomainId.PARALLELISM, ParallelismEventId.TASK_FINISHED),
                is(equalTo(2048)));
    }

    private void assertCalculationFailed(Target targetId, DomainId domainId, EventId eventId, int expectedErrorValue) {
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
        @SuppressWarnings("unused") InitializeEvent ev = Event.tryCast(new ShutdownPerformedEvent());
    }

    @Test
    public void eventTypeShouldBeAPlaceholderMethod() {
        expectedException.expect(UnsupportedOperationException.class);
        Event.eventType();
    }
}
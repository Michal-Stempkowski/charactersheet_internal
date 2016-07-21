package com.github.michal_stempkowski.charactersheet.internal.events;

import org.junit.Test;

import java.rmi.server.UID;
import java.util.function.Consumer;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsSame.sameInstance;

public class EventConnectionTest {
    @Test
    public void shouldHoldDataRequiredForMaintainingConnectionWithEvent() {
        // Given:
        int eventType = 5;
        Consumer<Event> consumer = (Event e) -> {};
        UID identifier = new UID();
        // When:
        EventConnection conn = new EventConnection(eventType, consumer);
        EventConnection conn2 = new EventConnection(eventType, consumer);
        // Then:
        assertThat(conn.getEventType(), is(equalTo(eventType)));
        assertThat(conn.getEventConsumer(), is(sameInstance(consumer)));
        assertThat(conn2.getEventType(), is(equalTo(eventType)));
        assertThat(conn2.getEventConsumer(), is(sameInstance(consumer)));
        assertThat(conn.getConnectionId(), is(not(equalTo(conn2.getConnectionId()))));
    }
}
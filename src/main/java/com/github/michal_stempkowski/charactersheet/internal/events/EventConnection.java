package com.github.michal_stempkowski.charactersheet.internal.events;

import java.rmi.server.UID;
import java.util.function.Consumer;

/**
 * Class which instances are created by EventDispatcher in order to give subscribers some way to unsubscribe. It holds
 * information about type of event, method called on event and unique connection id.
 */
public class EventConnection {
    private final int eventType;
    private final Consumer<Event> eventConsumer;
    private final UID connectionId;

    public EventConnection(int eventType, Consumer<Event> eventConsumer) {

        this.eventType = eventType;
        this.eventConsumer = eventConsumer;
        this.connectionId = new UID();
    }

    public int getEventType() {
        return eventType;
    }

    public Consumer<Event> getEventConsumer() {
        return eventConsumer;
    }

    public UID getConnectionId() {
        return connectionId;
    }
}

package com.github.michal_stempkowski.charactersheet.internal.events;

import com.github.michal_stempkowski.charactersheet.internal.EventId;

/**
 * Enum used to differentiate between different event ids in 'event' domain
 */
public enum EventEventId implements EventId {
    INITIALIZE(0),
    SHUTDOWN_PERFORMED_EVENT(1);

    private final int id;

    EventEventId(int id) {
        this.id = id;
    }

    @Override
    public int id() {
        return id;
    }
}

package com.github.michal_stempkowski.charactersheet.internal.events;

/**
 * Enum used to differentiate between different event ids in 'event' domain
 */
public enum EventEventId {
    INITIALIZE(0),
    SHUTDOWN_PERFORMED_EVENT(1);

    public final int id;

    EventEventId(int id) {
        this.id = id;
    }
}

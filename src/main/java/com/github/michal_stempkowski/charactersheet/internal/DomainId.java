package com.github.michal_stempkowski.charactersheet.internal;

/**
 * Enum used to differentiate between different domains inside internal target.
 */
public enum DomainId {
    EVENT(0),
    LOGGING(1),
    PARALLELISM(2);

    public final int id;

    DomainId(int id) {
        this.id = id;
    }
}

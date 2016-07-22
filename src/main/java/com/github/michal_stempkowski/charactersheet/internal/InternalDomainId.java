package com.github.michal_stempkowski.charactersheet.internal;

/**
 * Enum used to differentiate between different domains inside internal target.
 */
public enum InternalDomainId implements DomainId {
    EVENT(0),
    LOGGING(1),
    PARALLELISM(2);

    private final int id;

    InternalDomainId(int id) {
        this.id = id;
    }

    @Override
    public int id() {
        return id;
    }
}

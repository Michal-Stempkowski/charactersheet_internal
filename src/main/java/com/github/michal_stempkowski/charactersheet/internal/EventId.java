package com.github.michal_stempkowski.charactersheet.internal;

/**
 * Common interface for all event ids.
 */
public interface EventId {
    int id();
    default String name() {
        return toString();
    }
}

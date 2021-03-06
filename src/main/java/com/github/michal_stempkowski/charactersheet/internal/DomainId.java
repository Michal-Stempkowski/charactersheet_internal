package com.github.michal_stempkowski.charactersheet.internal;

/**
 * Common interface for domain identifiers across all targets.
 */
public interface DomainId {
    int id();
    default String name() {
        return toString();
    }
}

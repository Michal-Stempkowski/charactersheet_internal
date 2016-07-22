package com.github.michal_stempkowski.charactersheet.internal.parallelism;

import com.github.michal_stempkowski.charactersheet.internal.EventId;

/**
 * Enum used to differentiate between different event ids in 'parallelism' domain
 */
public enum ParallelismEventId implements EventId{
    TASK_FINISHED(0);

    private final int id;

    ParallelismEventId(int id) {
        this.id = id;
    }

    @Override
    public int id() {
        return id;
    }
}

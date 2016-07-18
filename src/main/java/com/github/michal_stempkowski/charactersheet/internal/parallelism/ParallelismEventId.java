package com.github.michal_stempkowski.charactersheet.internal.parallelism;

/**
 * Enum used to differentiate between different event ids in 'parallelism' domain
 */
public enum ParallelismEventId {
    TASK_FINISHED(0);

    public final int id;

    ParallelismEventId(int id) {
        this.id = id;
    }
}

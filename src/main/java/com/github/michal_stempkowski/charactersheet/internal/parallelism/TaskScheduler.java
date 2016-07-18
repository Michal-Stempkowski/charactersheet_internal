package com.github.michal_stempkowski.charactersheet.internal.parallelism;

/**
 * Interface for class responsible for scheduling and running long running tasks.
 */
public interface TaskScheduler {
    void scheduleTask(CyclingTask task);
    void init();
    int tasksInQueue();
}

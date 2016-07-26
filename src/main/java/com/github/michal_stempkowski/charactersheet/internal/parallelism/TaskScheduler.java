package com.github.michal_stempkowski.charactersheet.internal.parallelism;

import java.time.Duration;

/**
 * Interface for class responsible for scheduling and running long running tasks.
 */
public interface TaskScheduler {
    void scheduleTask(CyclingTask task);
    void init();
    int tasksInQueue();
    void gentleShutdown(Duration finalizationTime, Duration lastResortTime);
}

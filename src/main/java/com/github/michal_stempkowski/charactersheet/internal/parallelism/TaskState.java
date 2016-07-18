package com.github.michal_stempkowski.charactersheet.internal.parallelism;

/**
 * Class for representation of current state of CyclingTask.
 */
public enum TaskState {
    CREATED(false, false, false),
    RUNNING(true, false, false),
    DONE(true, true, false),
    ERROR(true, true, true);

    private final boolean started;
    private final boolean finished;
    private final boolean abnormalState;

    TaskState(boolean hasStarted, boolean isFinished, boolean inAbnormalState) {
        started = hasStarted;
        finished = isFinished;
        abnormalState = inAbnormalState;
    }

    public boolean hasStarted() {
        return started;
    }

    public boolean hasFinished() {
        return finished;
    }

    public boolean inAbnormalState() {
        return abnormalState;
    }
}

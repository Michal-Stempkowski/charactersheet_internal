package com.github.michal_stempkowski.charactersheet.internal.parallelism;

import com.github.michal_stempkowski.charactersheet.internal.utils.ErrorMonad;

import java.rmi.server.UID;
import java.util.function.BiFunction;

/**
 * Class designed for creating long running tasks. Long running tasks are expected to be
 * divided into arbitrarily short-running methods after which whole state of task
 * is to be evaluated. Evaluation may cause task removal, changing next method to be run
 * (for example internal state of task has been changed, and now it requires to use
 * different logic on next iteration cycle).
 *
 * Tasks using that interface should be designed to be run both sequentially (each step is
 * performed for all tasks, and after that task is allowed to progress to next iteration step)
 * and in parallel (each task uses existing thread pool in order to execute its next iteration
 * and task evaluation, although there is no guarantee for order).
 */
public class CyclingTask {
    public final UID id;

    private ThrowableRunnable cycleFunction;
    private BiFunction<TaskState, ErrorMonad, TaskState> taskStateEvaluator;
    private ErrorMonad error;
    private TaskState state;

    public CyclingTask(ThrowableRunnable cycleFunction,
                       BiFunction<TaskState, ErrorMonad, TaskState> taskStateEvaluator) {
        this(cycleFunction, taskStateEvaluator, new UID());
    }

    public CyclingTask(ThrowableRunnable cycleFunction,
                       BiFunction<TaskState, ErrorMonad, TaskState> taskStateEvaluator,
                       UID identifier) {
        this.id = identifier;
        this.cycleFunction = cycleFunction;
        this.taskStateEvaluator = taskStateEvaluator;
        error = new ErrorMonad();
        state = TaskState.CREATED;
    }

    public ErrorMonad runSingleCycle() {
        try {
            cycleFunction.run();
            error = new ErrorMonad();
        } catch (Exception e) {
            error = new ErrorMonad(e);
        }

        return error;
    }

    public TaskState evaluateTask() {
        state = taskStateEvaluator.apply(state, error);
        return state;
    }

    public TaskState getState() {
        return state;
    }
}

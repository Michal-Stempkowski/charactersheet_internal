package com.github.michal_stempkowski.charactersheet.internal.parallelism;

import com.github.michal_stempkowski.charactersheet.internal.utils.ErrorMonad;
import org.junit.Before;
import org.junit.Test;

import java.util.function.BiFunction;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class CyclingTaskTest {
    private ThrowableRunnable cycleWithError;
    private ThrowableRunnable cycleWithNoError;
    private BiFunction<TaskState, ErrorMonad, TaskState> taskEvaluatorIgnoreAllErrorsAndFinish;
    private CyclingTask uut;

    @Before
    public void setUp() {
        cycleWithError = () -> { throw new RuntimeException(); };
        cycleWithNoError = () -> {};
        taskEvaluatorIgnoreAllErrorsAndFinish = (TaskState state, ErrorMonad error) -> TaskState.DONE;
    }

    @Test
    public void onUnhandledErrorInCycleErrorShouldBePutToMonad() {
        // Given:
        uut = new CyclingTask(cycleWithError, taskEvaluatorIgnoreAllErrorsAndFinish);
        // When:
        ErrorMonad result = uut.runSingleCycle();
        uut.evaluateTask();
        // Then:
        assertThat(result.hasErrorOccurred(), is(true));
        assertThat(uut.getState(), is(equalTo(TaskState.DONE)));
    }

    @Test
    public void onNoErrorInCycleErrorMonadShouldBeEmpty() {
        // Given:
        uut = new CyclingTask(cycleWithNoError, taskEvaluatorIgnoreAllErrorsAndFinish);
        // When:
        ErrorMonad result = uut.runSingleCycle();
        uut.evaluateTask();
        // Then:
        assertThat(result.hasErrorOccurred(), is(false));
        assertThat(uut.getState(), is(equalTo(TaskState.DONE)));
    }
}
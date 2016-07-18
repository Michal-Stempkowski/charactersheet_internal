package com.github.michal_stempkowski.charactersheet.internal.events;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Hook returned by EventDispatcher.notifyEvent. Allows blocking until all event handling is finished.
 */
public interface EventBlocker {
    void block(Duration d) throws ExecutionException, InterruptedException, TimeoutException;
    boolean hasFinished();
}

package com.github.michal_stempkowski.charactersheet.internal.parallelism;

/**
 * Interface for implementation of runnable that is allowed to throw.
 */
public interface ThrowableRunnable {
    void run() throws Exception;
}

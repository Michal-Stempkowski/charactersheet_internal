package com.github.michal_stempkowski.charactersheet.internal.app;

import com.github.michal_stempkowski.charactersheet.internal.utils.ErrorMonad;

/**
 * Interface responsible for holding all domain-global data. It may may also
 * contain some common logic or run long lasting tasks
 */
public interface Domain {
    ErrorMonad getStatus();
    void setup();
}

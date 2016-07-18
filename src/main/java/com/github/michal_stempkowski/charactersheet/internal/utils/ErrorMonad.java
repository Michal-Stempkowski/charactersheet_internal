package com.github.michal_stempkowski.charactersheet.internal.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * Monad containing information of failure that has occurred.
 */
public class ErrorMonad {
    private final List<Exception> exceptionList;

    public ErrorMonad(Exception ex) {
        this();
        this.exceptionList.add(ex);
    }

    public ErrorMonad() {
        this.exceptionList = new LinkedList<>();
    }

    public boolean hasErrorOccurred() {
        return !exceptionList.isEmpty();
    }

    public ErrorMonad join(ErrorMonad other) {
        exceptionList.addAll(other.exceptionList);
        return this;
    }

    public List<Exception> get() {
        return exceptionList;
    }
}

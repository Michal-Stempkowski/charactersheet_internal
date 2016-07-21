package com.github.michal_stempkowski.charactersheet.internal.utils;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;

public class ErrorMonadTest {
    @Test
    public void noErrorMonadEmpty() {
        // When:
        ErrorMonad uut = new ErrorMonad();
        // Then:
        assertThat(uut.hasErrorOccurred(), is(false));
        List<Exception> errors = uut.get();
        assertThat(errors.size(), is(equalTo(0)));
    }

    @Test
    public void onErrorMonadStoresIt() {
        // Given:
        @SuppressWarnings("ThrowableInstanceNeverThrown") Exception ex = new RuntimeException();
        // When:
        ErrorMonad uut = new ErrorMonad(ex);
        // Then:
        assertThat(uut.hasErrorOccurred(), is(true));
        hasErrorsOnErrorList(uut, ex);
    }

    private void hasErrorsOnErrorList(ErrorMonad monad, Exception... exceptions) {
        List<Exception> errors = monad.get();
        assertThat(errors.size(), is(equalTo(exceptions.length)));
        assertThat(errors, hasItems(exceptions));
    }

    @Test
    public void monadJoiningShouldWork() {
        // Given:
        @SuppressWarnings("ThrowableInstanceNeverThrown") Exception ex = new RuntimeException();
        @SuppressWarnings("ThrowableInstanceNeverThrown") Exception ex2 = new Exception();
        // When/Then:
        ErrorMonad uut = new ErrorMonad(ex);
        hasErrorsOnErrorList(uut, ex);
        // When/Then:
        uut.join(new ErrorMonad(ex2));
        hasErrorsOnErrorList(uut, ex, ex2);
        // When/Then:
        uut.join(new ErrorMonad());
        hasErrorsOnErrorList(uut, ex, ex2);
    }
}
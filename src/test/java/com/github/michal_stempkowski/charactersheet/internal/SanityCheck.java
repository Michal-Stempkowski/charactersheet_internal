package com.github.michal_stempkowski.charactersheet.internal;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SanityCheck {
    @Test
    public void shouldBeSane() {
        assertThat(true, is(equalTo(true)));
    }
}


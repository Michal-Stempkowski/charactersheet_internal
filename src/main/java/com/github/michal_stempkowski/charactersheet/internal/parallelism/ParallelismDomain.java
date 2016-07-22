package com.github.michal_stempkowski.charactersheet.internal.parallelism;

import com.github.michal_stempkowski.charactersheet.internal.app.AppRootLogic;
import com.github.michal_stempkowski.charactersheet.internal.app.Domain;
import com.github.michal_stempkowski.charactersheet.internal.events.Event;
import com.github.michal_stempkowski.charactersheet.internal.events.events.InitializeEvent;
import com.github.michal_stempkowski.charactersheet.internal.utils.ErrorMonad;

/**
 *Domain responsible for running long tasks inside threads
 */
public class ParallelismDomain implements Domain {
    private final ErrorMonad status = new ErrorMonad();

    @Override
    public ErrorMonad getStatus() {
        return status;
    }

    @Override
    public void setup() {
        AppRootLogic.getTaskScheduler();
        AppRootLogic.getEventDispatcher().registerListener(InitializeEvent.eventType(), this::onInitialize);
    }

    private void onInitialize(Event event) {
        @SuppressWarnings("unused") InitializeEvent ev = Event.tryCast(event);
        AppRootLogic.getTaskScheduler().init();
    }
}

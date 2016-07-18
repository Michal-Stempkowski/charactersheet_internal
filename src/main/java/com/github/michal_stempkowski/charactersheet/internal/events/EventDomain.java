package com.github.michal_stempkowski.charactersheet.internal.events;

import com.github.michal_stempkowski.charactersheet.internal.app.AppRootLogic;
import com.github.michal_stempkowski.charactersheet.internal.app.Domain;
import com.github.michal_stempkowski.charactersheet.internal.utils.ErrorMonad;

/**
 * Domain responsible for creation and managing of EventDispatcher instance.
 */
public class EventDomain implements Domain {

    private final ErrorMonad status = new ErrorMonad();

    @Override
    public ErrorMonad getStatus() {
        return status;
    }

    @Override
    public void setup() {
        AppRootLogic.getEventDispatcher();
    }
}

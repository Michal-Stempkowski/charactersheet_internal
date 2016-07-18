package com.github.michal_stempkowski.charactersheet.internal.events.events;

import com.github.michal_stempkowski.charactersheet.internal.DomainId;
import com.github.michal_stempkowski.charactersheet.internal.Target;
import com.github.michal_stempkowski.charactersheet.internal.events.Event;
import com.github.michal_stempkowski.charactersheet.internal.events.EventEventId;

/**
 * First event sent in the system.
 */
public class InitializeEvent extends Event{
    public InitializeEvent() {
        super(InitializeEvent.eventType());
    }

    public static int eventType() {
        return calculateEventType(Target.INTERNAL.id, DomainId.EVENT.id, EventEventId.INITIALIZE.id);
    }
}

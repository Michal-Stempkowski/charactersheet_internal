package com.github.michal_stempkowski.charactersheet.internal.events.events;

import com.github.michal_stempkowski.charactersheet.internal.InternalDomainId;
import com.github.michal_stempkowski.charactersheet.internal.Target;
import com.github.michal_stempkowski.charactersheet.internal.events.Event;
import com.github.michal_stempkowski.charactersheet.internal.events.EventEventId;

/**
 * Last event sent in the system.
 */
public class ShutdownPerformedEvent extends Event{
    public ShutdownPerformedEvent() {
        super(ShutdownPerformedEvent.eventType());
    }

    public static int eventType() {
        return calculateEventType(Target.INTERNAL, InternalDomainId.EVENT, EventEventId.SHUTDOWN_PERFORMED_EVENT);
    }
}

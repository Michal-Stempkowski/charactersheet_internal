package com.github.michal_stempkowski.charactersheet.internal.events.events;

import com.github.michal_stempkowski.charactersheet.internal.DomainId;
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
        return calculateEventType(Target.INTERNAL.id, DomainId.EVENT.id, EventEventId.SHUTDOWN_PERFORMED_EVENT.id);
    }
}

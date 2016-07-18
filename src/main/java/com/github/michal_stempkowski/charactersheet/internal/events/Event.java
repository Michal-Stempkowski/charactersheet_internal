package com.github.michal_stempkowski.charactersheet.internal.events;

import com.github.michal_stempkowski.charactersheet.internal.Target;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Basic system event.
 */
public class Event {
    public final int eventType;
    protected final List<Object> args;

    public Event(int eventType, Object...args) {
        this.eventType = eventType;
        this.args =new ArrayList<>(Arrays.asList(args));
    }

    public static int calculateEventType(int targetId, int domainId, int eventId) {
        if (targetId < 0) {
            throw new EventTypeCalculationError(targetId);
        } else if ( domainId < 0 || domainId >= Target.Consts.DOMAINS_ALLOWED) {
            throw new EventTypeCalculationError(domainId);
        } else if ((eventId < 0 || eventId >= Target.Consts.EVENTS_IN_DOMAIN_ALLOWED)) {
            throw new EventTypeCalculationError((eventId));
        } else {
            return (targetId * Target.Consts.DOMAINS_ALLOWED + domainId) *
                    Target.Consts.EVENTS_IN_DOMAIN_ALLOWED + eventId;
        }
    }

    public static int eventType() {
        throw new UnsupportedOperationException();
    }

    public static class EventTypeCalculationError extends RuntimeException {
        public final int errorValue;

        public EventTypeCalculationError(int errorValue) {
            this.errorValue = errorValue;
        }
    }

    public static <T extends Event> T tryCast(Event event) {
        return (T)event;
    }
}

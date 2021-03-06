package com.github.michal_stempkowski.charactersheet.internal.events;

import com.github.michal_stempkowski.charactersheet.internal.DomainId;
import com.github.michal_stempkowski.charactersheet.internal.EventId;
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

    public static int calculateEventType(Target target, DomainId domain, EventId event) {
        if (target.id() < 0) {
            throw new EventTypeCalculationError(target.id());
        } else if ( domain.id() < 0 || domain.id() >= Target.Consts.DOMAINS_ALLOWED) {
            throw new EventTypeCalculationError(domain.id());
        } else if ((event.id() < 0 || event.id() >= Target.Consts.EVENTS_IN_DOMAIN_ALLOWED)) {
            throw new EventTypeCalculationError((event.id()));
        } else {
            return (target.id() * Target.Consts.DOMAINS_ALLOWED + domain.id()) *
                    Target.Consts.EVENTS_IN_DOMAIN_ALLOWED + event.id();
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

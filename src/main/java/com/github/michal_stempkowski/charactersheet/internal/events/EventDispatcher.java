package com.github.michal_stempkowski.charactersheet.internal.events;

import java.time.Duration;
import java.util.function.Consumer;

/**
 * Class responsible for managing system events. It groups events into some categories, to which subscribers can subscribe.
 * It allows events from different components to remain separated from each other.
 */
public interface EventDispatcher {
    void gentleShutdown(Duration finalizationTime, Duration lastResortTime);
    EventConnection registerListener(int eventType, Consumer<Event> eventConsumer);
    EventBlocker notifyEvent(Event e);
    void unregisterListener(EventConnection conn) throws Exception;
}

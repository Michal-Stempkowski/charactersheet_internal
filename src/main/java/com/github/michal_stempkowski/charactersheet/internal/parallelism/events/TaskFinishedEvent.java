package com.github.michal_stempkowski.charactersheet.internal.parallelism.events;

import com.github.michal_stempkowski.charactersheet.internal.InternalDomainId;
import com.github.michal_stempkowski.charactersheet.internal.Target;
import com.github.michal_stempkowski.charactersheet.internal.events.Event;
import com.github.michal_stempkowski.charactersheet.internal.parallelism.CyclingTask;
import com.github.michal_stempkowski.charactersheet.internal.parallelism.ParallelismEventId;

/**
 * Event sent when long running task is finished.
 */
public class TaskFinishedEvent extends Event {
    public TaskFinishedEvent(CyclingTask task) {
        super(eventType(), task);
    }

    public static int eventType() {
        return calculateEventType(Target.INTERNAL, InternalDomainId.PARALLELISM, ParallelismEventId.TASK_FINISHED);
    }

    public CyclingTask getTask() {
        return (CyclingTask) args.get(0);
    }
}

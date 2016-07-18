package com.github.michal_stempkowski.charactersheet.internal.parallelism.events;

import com.github.michal_stempkowski.charactersheet.internal.DomainId;
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
        return calculateEventType(Target.INTERNAL.id, DomainId.PARALLELISM.id, ParallelismEventId.TASK_FINISHED.id);
    }

    public CyclingTask getTask() {
        return (CyclingTask) args.get(0);
    }
}

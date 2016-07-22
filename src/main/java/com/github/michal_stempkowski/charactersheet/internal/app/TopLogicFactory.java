package com.github.michal_stempkowski.charactersheet.internal.app;

import com.github.michal_stempkowski.charactersheet.internal.Target;
import com.github.michal_stempkowski.charactersheet.internal.events.EventDispatcher;
import com.github.michal_stempkowski.charactersheet.internal.parallelism.TaskScheduler;

import java.util.List;
import java.util.logging.Logger;

/**
 * Class responsible for providing target-dependant top logic.
 */
public interface TopLogicFactory {
    List<PackageInitializer> getPackageInitializers();

    EventDispatcher getEventDispatcher();

    Logger createLogger(Target targetId, int domainId, String name);

    void start();

    TaskScheduler getTaskScheduler();
}

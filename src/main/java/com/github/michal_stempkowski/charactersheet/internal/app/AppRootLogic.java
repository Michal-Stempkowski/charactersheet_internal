package com.github.michal_stempkowski.charactersheet.internal.app;

import com.github.michal_stempkowski.charactersheet.internal.Target;
import com.github.michal_stempkowski.charactersheet.internal.events.EventDispatcher;
import com.github.michal_stempkowski.charactersheet.internal.parallelism.TaskScheduler;
import com.github.michal_stempkowski.charactersheet.internal.utils.ErrorMonad;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Class responsible for top level application initialization and global state sharing.
 * It is target independent, relying on its external components to do all the target
 * dependant work.
 */
public class AppRootLogic {

    private static TopLogicFactory topLogicFactory = null;
    private static List<Domain> domains = new ArrayList<>();

    public AppRootLogic(TopLogicFactory topLogicFactory) {
        AppRootLogic.topLogicFactory = topLogicFactory;
    }

    public static boolean init() {
        domains = AppRootLogic.topLogicFactory
                .getPackageInitializers().stream()
                .flatMap(x -> x.init().stream())
                .collect(Collectors.toList());
        return AppRootLogic.domains.stream().map(Domain::getStatus).noneMatch(ErrorMonad::hasErrorOccurred);
    }

    public static EventDispatcher getEventDispatcher() {
        return AppRootLogic.topLogicFactory.getEventDispatcher();
    }

    public static Logger createLogger(Target targetId, int domainId, String name) {
        return AppRootLogic.topLogicFactory.createLogger(targetId, domainId, name);
    }

    public static void start() {
        AppRootLogic.topLogicFactory.start();
    }

    public static TaskScheduler getTaskScheduler() {
        return AppRootLogic.topLogicFactory.getTaskScheduler();
    }
}

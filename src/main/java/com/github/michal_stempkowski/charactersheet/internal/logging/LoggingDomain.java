package com.github.michal_stempkowski.charactersheet.internal.logging;

import com.github.michal_stempkowski.charactersheet.internal.app.AppRootLogic;
import com.github.michal_stempkowski.charactersheet.internal.DomainId;
import com.github.michal_stempkowski.charactersheet.internal.Target;
import com.github.michal_stempkowski.charactersheet.internal.app.Domain;
import com.github.michal_stempkowski.charactersheet.internal.utils.ErrorMonad;

import java.util.logging.Logger;

/**
 * Domain responsible for handling logging system
 */
public class LoggingDomain implements Domain {

    private final ErrorMonad status = new ErrorMonad();
    private Logger logger;

    @Override
    public ErrorMonad getStatus() {
        return status;
    }

    @Override
    public void setup() {
        logger = AppRootLogic.createLogger(Target.INTERNAL, DomainId.LOGGING, getClass().getName());
        logger.info("Logging system has started successfully!");
    }
}

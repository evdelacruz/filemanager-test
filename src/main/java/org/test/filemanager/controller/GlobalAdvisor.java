package org.test.filemanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
public class GlobalAdvisor {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalAdvisor.class);
    private static final Map<Class<? extends Exception>, Function<Exception, ResponseEntity<Error>>> EXCEPTIONS = new HashMap<>();

    static {

    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleException(Exception ex) {
        if (EXCEPTIONS.containsKey(ex.getClass())) {
            LOGGER.debug("Expected exception handled !!!", ex);
            return EXCEPTIONS.get(ex.getClass()).apply(ex);
        }
        LOGGER.error("Unexpected exception handled !!!", ex);
        return status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error");
    }
}

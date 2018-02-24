package org.test.filemanager.controller;

import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.springframework.http.ResponseEntity.*;

@ControllerAdvice
public class GlobalAdvisor {
    private static final Logger logger = LoggerFactory.getLogger(GlobalAdvisor.class);
    private final Map<Class<? extends Exception>, Function<Exception, ResponseEntity>> exceptions = new HashMap<>();

    public GlobalAdvisor() {
        exceptions.put(MultipartException.class, ex -> this.getResponse((MultipartException) ex));
        exceptions.put(IllegalArgumentException.class, ex -> badRequest().body(ex.getMessage()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleException(Exception ex) {
        if (exceptions.containsKey(ex.getClass())) {
            logger.debug("Expected exception handled !!!", ex);
            return exceptions.get(ex.getClass()).apply(ex);
        }
        logger.error("Unexpected exception handled !!!", ex);
        return status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error");
    }

    //<editor-fold desc="Support methods">
    private ResponseEntity getResponse(MultipartException ex) {
        Throwable root = ex.getMostSpecificCause();
        String message = root instanceof FileUploadBase.FileSizeLimitExceededException
                ? String.format("The file exceeds its maximum permitted size \"%s\"", ((FileUploadBase.FileSizeLimitExceededException) root).getPermittedSize())
                : ex.getMessage();
        return badRequest().body(message);
    }
    //</editor-fold>
}

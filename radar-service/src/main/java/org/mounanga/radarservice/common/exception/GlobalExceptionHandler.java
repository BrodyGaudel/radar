package org.mounanga.radarservice.common.exception;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RadarNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handle(@NotNull RadarNotFoundException exception) {
        return ResponseEntity.status(NOT_FOUND).body(new ExceptionResponse(
                NOT_FOUND.value(),
                exception.getMessage(),
                exception.getLocalizedMessage(),
                getCauseOfException(exception.getCause())
        ));
    }

    @ExceptionHandler(InvalidObjectIdException.class)
    public ResponseEntity<ExceptionResponse> handle(@NotNull InvalidObjectIdException exception) {
        return ResponseEntity.status(BAD_REQUEST).body(new ExceptionResponse(
                BAD_REQUEST.value(),
                exception.getMessage(),
                exception.getLocalizedMessage(),
                getCauseOfException(exception.getCause())
        ));
    }

    @ExceptionHandler(NegativeSpeedException.class)
    public ResponseEntity<ExceptionResponse> handle(@NotNull NegativeSpeedException exception) {
        return ResponseEntity.status(BAD_REQUEST).body(new ExceptionResponse(
                BAD_REQUEST.value(),
                exception.getMessage(),
                exception.getLocalizedMessage(),
                getCauseOfException(exception.getCause())
        ));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handle(@NotNull Exception exception) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ExceptionResponse(
                INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                exception.getLocalizedMessage(),
                getCauseOfException(exception.getCause())
        ));
    }

    private String getCauseOfException(Throwable throwable) {
        if(throwable == null) {
            return "We have not found explicit causes.";
        }
        return throwable.toString();
    }


}

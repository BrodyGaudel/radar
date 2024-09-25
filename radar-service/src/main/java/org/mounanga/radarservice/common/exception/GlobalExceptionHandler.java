package org.mounanga.radarservice.common.exception;

import org.jetbrains.annotations.NotNull;
import org.mounanga.radarservice.commands.exception.NegativeSpeedException;
import org.mounanga.radarservice.queries.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleException(@NotNull ResourceNotFoundException exception) {
        return ResponseEntity.status(NOT_FOUND).body( new ExceptionResponseDTO(
                NOT_FOUND.value(),
                exception.getMessage(),
                exception.getLocalizedMessage(),
                new HashSet<>()
        ));
    }

    @ExceptionHandler(NegativeSpeedException.class)
    public ResponseEntity<ExceptionResponseDTO> handleException(@NotNull NegativeSpeedException exception) {
        return ResponseEntity.status(BAD_REQUEST).body( new ExceptionResponseDTO(
                BAD_REQUEST.value(),
                exception.getMessage(),
                exception.getLocalizedMessage(),
                new HashSet<>()
        ));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ExceptionResponseDTO> handleException(@NotNull IllegalStateException exception) {
        return ResponseEntity.status(CONFLICT).body( new ExceptionResponseDTO(
                CONFLICT.value(),
                exception.getMessage(),
                exception.getLocalizedMessage(),
                new HashSet<>()
        ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDTO> handleException(@NotNull MethodArgumentNotValidException exception) {
        Set<String> validationErrors = new HashSet<>();
        exception.getBindingResult().getAllErrors().forEach(error -> validationErrors.add(error.getDefaultMessage()));

        return ResponseEntity.status(BAD_REQUEST).body(new ExceptionResponseDTO(
                BAD_REQUEST.value(),
                exception.getMessage(),
                exception.getLocalizedMessage(),
                validationErrors
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleException(@NotNull Exception exception) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ExceptionResponseDTO(
                INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                exception.getLocalizedMessage(),
                new HashSet<>()
        ));
    }
}

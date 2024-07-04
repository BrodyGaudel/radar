package org.mounanga.radarservice.common.exception;

public record ExceptionResponse(Integer code,
                                String message,
                                String description,
                                String cause)  {
}

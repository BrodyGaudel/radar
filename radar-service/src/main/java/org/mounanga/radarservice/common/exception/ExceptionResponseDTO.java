package org.mounanga.radarservice.common.exception;

import java.util.Set;

public record ExceptionResponseDTO(Integer code,
                                   String Message,
                                   String Description,
                                   Set<String> validation) {
}

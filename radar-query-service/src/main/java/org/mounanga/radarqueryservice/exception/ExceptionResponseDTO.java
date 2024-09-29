package org.mounanga.radarqueryservice.exception;

import java.util.Set;

public record ExceptionResponseDTO(Integer code,
                                   String Message,
                                   String Description,
                                   Set<String> validation) {
}

package org.mounanga.infractionservice.commands.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class ItemNotFoundException extends RuntimeException {

    private final List<String> errors;

    public ItemNotFoundException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }
}

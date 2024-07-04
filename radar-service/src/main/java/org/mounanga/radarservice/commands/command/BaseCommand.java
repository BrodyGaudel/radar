package org.mounanga.radarservice.commands.command;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
public class BaseCommand<T> {

    @TargetAggregateIdentifier
    private T id;

    public BaseCommand(T id) {
        this.id = id;
    }
}

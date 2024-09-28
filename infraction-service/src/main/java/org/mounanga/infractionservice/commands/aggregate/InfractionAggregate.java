package org.mounanga.infractionservice.commands.aggregate;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.jetbrains.annotations.NotNull;
import org.mounanga.infractionservice.commands.command.CreateInfractionCommand;
import org.mounanga.infractionservice.commands.command.DeleteInfractionCommand;
import org.mounanga.infractionservice.commands.command.UpdateInfractionCommand;
import org.mounanga.infractionservice.commands.util.EventFactory;
import org.mounanga.infractionservice.common.event.InfractionCreatedEvent;
import org.mounanga.infractionservice.common.event.InfractionDeletedEvent;
import org.mounanga.infractionservice.common.event.InfractionUpdatedEvent;

import java.time.LocalDateTime;

@Aggregate
@Slf4j
@Getter
public class InfractionAggregate {

    @AggregateIdentifier
    private String infractionId;

    private LocalDateTime dateTime;

    private Double speed;

    private Double amount;

    private String vehicleId;

    private String radarId;

    public InfractionAggregate() {
        super();
    }

    @CommandHandler
    public InfractionAggregate(CreateInfractionCommand command) {
        log.info("Handling CreateInfractionCommand");
        InfractionCreatedEvent event = EventFactory.create(command);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateInfractionCommand command) {
        log.info("Handling UpdateInfractionCommand");
        InfractionUpdatedEvent event = EventFactory.create(command);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(DeleteInfractionCommand command) {
        log.info("Handling DeleteInfractionCommand");
        InfractionDeletedEvent event = EventFactory.create(command);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(@NotNull InfractionCreatedEvent event) {
        log.info("Handling InfractionCreatedEvent");
        this.infractionId = event.getId();
        this.dateTime = event.getDateTime();
        this.speed = event.getSpeed();
        this.amount = event.getAmount();
        this.vehicleId = event.getVehicleId();
        this.radarId = event.getRadarId();
    }

    @EventSourcingHandler
    public void on(@NotNull InfractionUpdatedEvent event) {
        log.info("Handling InfractionUpdatedEvent");
        this.infractionId = event.getId();
        this.dateTime = event.getDateTime();
        this.speed = event.getSpeed();
        this.amount = event.getAmount();
        this.vehicleId = event.getVehicleId();
        this.radarId = event.getRadarId();
    }

    @EventSourcingHandler
    public void on(@NotNull InfractionDeletedEvent event) {
        log.info("Handling InfractionDeletedEvent");
        this.infractionId = event.getId();
        this.dateTime = null;
        this.speed = null;
        this.amount = null;
        this.vehicleId = null;
        this.radarId = null;
    }

}
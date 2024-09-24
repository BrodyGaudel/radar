package org.mounanga.registrationservice.commands.aggregate;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.jetbrains.annotations.NotNull;
import org.mounanga.registrationservice.commands.command.vehicle.CreateVehicleCommand;
import org.mounanga.registrationservice.commands.command.vehicle.DeleteVehicleCommand;
import org.mounanga.registrationservice.commands.command.vehicle.UpdateVehicleCommand;
import org.mounanga.registrationservice.commands.util.factory.EventFactory;
import org.mounanga.registrationservice.common.event.vehicle.VehicleCreatedEvent;
import org.mounanga.registrationservice.common.event.vehicle.VehicleDeletedEvent;
import org.mounanga.registrationservice.common.event.vehicle.VehicleUpdatedEvent;

@Getter
@Slf4j
@Aggregate
public class VehicleAggregate {

    @AggregateIdentifier
    private String vehicleId;
    private String registrationId;
    private String marque;
    private String model;
    private Double taxPower;
    private String description;
    private String ownerId;

    public VehicleAggregate() {
        super();
    }

    @CommandHandler
    public VehicleAggregate(CreateVehicleCommand command) {
        log.info("Handle CreateVehicleCommand");
        VehicleCreatedEvent event = EventFactory.create(command);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateVehicleCommand command) {
        log.info("Handle UpdateVehicleCommand");
        VehicleUpdatedEvent event = EventFactory.create(command);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(DeleteVehicleCommand command) {
        log.info("Handle DeleteVehicleCommand");
        VehicleDeletedEvent event = EventFactory.create(command);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(@NotNull VehicleCreatedEvent event) {
        log.info("Handle VehicleCreatedEvent");
        setAggregateItems(event.getEventId(), event.getRegistrationId(), event.getMarque(), event.getModel(), event.getTaxPower(), event.getDescription(), event.getOwnerId());
    }

    @EventSourcingHandler
    public void on(@NotNull VehicleUpdatedEvent event) {
        log.info("Handle VehicleUpdatedEvent");
        setAggregateItems(event.getEventId(), event.getRegistrationId(), event.getMarque(), event.getModel(), event.getTaxPower(), event.getDescription(), event.getOwnerId());
    }

    @EventSourcingHandler
    public void on(@NotNull VehicleDeletedEvent event) {
        log.info("Handle VehicleDeletedEvent");
        setAggregateItems(event.getEventId(), null, null, null, null, null, null);
    }


    private void setAggregateItems(String eventId, String registrationId, String marque, String model, Double taxPower, String description, String ownerId) {
        this.vehicleId = eventId;
        this.registrationId = registrationId;
        this.marque = marque;
        this.model = model;
        this.taxPower = taxPower;
        this.description = description;
        this.ownerId = ownerId;
    }

}

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
import org.mounanga.registrationservice.common.event.vehicle.VehicleCreatedEvent;
import org.mounanga.registrationservice.common.event.vehicle.VehicleDeletedEvent;
import org.mounanga.registrationservice.common.event.vehicle.VehicleUpdatedEvent;

@Aggregate
@Slf4j
@Getter
public class VehicleAggregate {

    @AggregateIdentifier
    private String vehicleId;
    private String registrationId;
    private String marque;
    private String model;
    private String description;
    private Double taxPower;
    private String ownerId;

    public VehicleAggregate() {
    }

    @CommandHandler
    public VehicleAggregate(CreateVehicleCommand command) {
        log.info("CreateVehicleCommand handled");
        AggregateLifecycle.apply( new VehicleCreatedEvent(
                command.getId(),
                command.getRegistrationId(),
                command.getMarque(),
                command.getModel(),
                command.getDescription(),
                command.getTaxPower(),
                command.getOwnerId()
        ));
    }

    @EventSourcingHandler
    public void on(@NotNull VehicleCreatedEvent event) {
        log.info("VehicleCreatedEvent handled");
        this.vehicleId = event.getId();
        this.registrationId = event.getRegistrationId();
        this.model = event.getModel();
        this.marque = event.getMarque();
        this.description = event.getDescription();
        this.taxPower = event.getTaxPower();
        this.ownerId = event.getOwnerId();
    }

    @CommandHandler
    public void handle(@NotNull UpdateVehicleCommand command) {
        log.info("UpdateVehicleCommand handled");
        AggregateLifecycle.apply( new VehicleUpdatedEvent(
                command.getId(),
                command.getRegistrationId(),
                command.getMarque(),
                command.getModel(),
                command.getDescription(),
                command.getTaxPower(),
                command.getOwnerId()
        ));
    }

    @EventSourcingHandler
    public void on(@NotNull VehicleUpdatedEvent event) {
        log.info("VehicleUpdatedEvent handled");
        this.vehicleId = event.getId();
        this.registrationId = event.getRegistrationId();
        this.marque = event.getMarque();
        this.model = event.getModel();
        this.description = event.getDescription();
        this.taxPower = event.getTaxPower();
        this.ownerId = event.getOwnerId();
    }

    @CommandHandler
    public void handle(@NotNull DeleteVehicleCommand command) {
        log.info("DeleteVehicleCommand handled");
        AggregateLifecycle.apply( new VehicleDeletedEvent(command.getId()));
    }

    @EventSourcingHandler
    public void on(@NotNull VehicleDeletedEvent event) {
        log.info("VehicleDeletedEvent handled");
        this.vehicleId = event.getId();
        this.registrationId = null;
        this.marque = null;
        this.model = null;
        this.description = null;
        this.taxPower = null;
        this.ownerId = null;
        log.info("Vehicle state after deletion: vehicleId={}, registrationId={}, marque={}, model={}, description={}, taxPower={}, ownerId={}",
                this.vehicleId, this.registrationId, this.marque, this.model, this.description, this.taxPower, this.ownerId);
    }



}

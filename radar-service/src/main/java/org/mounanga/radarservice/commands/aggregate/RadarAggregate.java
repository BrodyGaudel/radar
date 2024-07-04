package org.mounanga.radarservice.commands.aggregate;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.jetbrains.annotations.NotNull;
import org.mounanga.radarservice.commands.command.CreateRadarCommand;
import org.mounanga.radarservice.commands.command.DeleteRadarCommand;
import org.mounanga.radarservice.commands.command.UpdateRadarCommand;
import org.mounanga.radarservice.common.event.RadarCreatedEvent;
import org.mounanga.radarservice.common.event.RadarDeletedEvent;
import org.mounanga.radarservice.common.event.RadarUpdatedEvent;
import org.mounanga.radarservice.common.exception.InvalidObjectIdException;
import org.mounanga.radarservice.common.exception.NegativeSpeedException;

@Getter
@Slf4j
@Aggregate
public class RadarAggregate {

    @AggregateIdentifier
    private String radarId;

    private Double speedLimit;

    private String address;

    private Double longitude;

    private Double latitude;

    public RadarAggregate() {}

    @CommandHandler
    public RadarAggregate(CreateRadarCommand command) {
        log.info("CreateRadarCommand handled");
        if(command.getSpeedLimit()<0){
            throw new NegativeSpeedException("Speed can not be negative");
        }
        AggregateLifecycle.apply(new RadarCreatedEvent(
                command.getId(),
                command.getSpeedLimit(),
                command.getAddress(),
                command.getLongitude(),
                command.getLatitude()
        ));
    }

    @EventSourcingHandler
    public void on(@NotNull RadarCreatedEvent event) {
        log.info("RadarCreatedEvent handled");
        this.radarId = event.getId();
        this.speedLimit = event.getSpeedLimit();
        this.address = event.getAddress();
        this.longitude = event.getLongitude();
        this.latitude = event.getLatitude();
    }

    @CommandHandler
    public void handle(@NotNull UpdateRadarCommand command) {
        log.info("UpdateRadarCommand handled");
        if (this.radarId == null) {
            throw new IllegalStateException("Cannot update a radar that does not exist");
        }
        if(command.getId() == null || command.getId().isBlank()){
            throw new InvalidObjectIdException("ID is not valid.");
        }
        if(command.getSpeedLimit()<0){
            throw new NegativeSpeedException("Speed can not be negative");
        }
        AggregateLifecycle.apply(new RadarUpdatedEvent(
                command.getId(),
                command.getSpeedLimit(),
                command.getAddress(),
                command.getLongitude(),
                command.getLatitude()
        ));
    }

    @EventSourcingHandler
    public void on(@NotNull RadarUpdatedEvent event) {
        log.info("RadarUpdatedEvent handled");
        this.radarId = event.getId();
        this.speedLimit = event.getSpeedLimit();
        this.address = event.getAddress();
        this.longitude = event.getLongitude();
        this.latitude = event.getLatitude();
    }

    @CommandHandler
    public void handle(@NotNull DeleteRadarCommand command) {
        log.info("DeleteRadarCommand handled");
        if(command.getId() == null || command.getId().isBlank()){
            throw new InvalidObjectIdException("ID is not valid.");
        }
        AggregateLifecycle.apply(new RadarDeletedEvent(command.getId()));
    }

    @EventSourcingHandler
    public void on(@NotNull RadarDeletedEvent event){
        log.info("RadarDeletedEvent handled");
        this.radarId = event.getId();
        this.speedLimit = null;
        this.address = null;
        this.longitude = null;
        this.latitude = null;
    }


}

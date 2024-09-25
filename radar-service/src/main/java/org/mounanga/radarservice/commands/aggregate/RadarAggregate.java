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
import org.mounanga.radarservice.commands.exception.NegativeSpeedException;
import org.mounanga.radarservice.commands.util.EventFactory;
import org.mounanga.radarservice.common.event.RadarCreatedEvent;
import org.mounanga.radarservice.common.event.RadarDeletedEvent;
import org.mounanga.radarservice.common.event.RadarUpdatedEvent;

@Getter
@Slf4j
@Aggregate
public class RadarAggregate {

    @AggregateIdentifier
    private String radarId;
    private String address;
    private Double latitude;
    private Double longitude;
    private Double speedLimit;

    public RadarAggregate() {
        super();
    }

    @CommandHandler
    public RadarAggregate(CreateRadarCommand command){
        log.info("Handling CreateRadarCommand");
        if(command.getSpeedLimit()<0){
            throw new NegativeSpeedException("Speed can not be negative");
        }
        RadarCreatedEvent event = EventFactory.create(command);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateRadarCommand command){
        if (this.radarId == null) {
            throw new IllegalStateException("Cannot update a radar that does not exist");
        }
        if(command.getSpeedLimit()<0){
            throw new NegativeSpeedException("Speed can not be negative");
        }
        log.info("Handling UpdateRadarCommand");
        RadarUpdatedEvent event = EventFactory.create(command);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(DeleteRadarCommand command){
        log.info("Handling DeleteRadarCommand");
        if (this.radarId == null) {
            throw new IllegalStateException("Cannot delete a radar that does not exist");
        }
        RadarDeletedEvent event = EventFactory.create(command);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(@NotNull RadarCreatedEvent event){
        log.info("Handling RadarCreatedEvent");
        this.radarId = event.getEventId();
        this.address = event.getAddress();
        this.latitude = event.getLatitude();
        this.longitude = event.getLongitude();
        this.speedLimit = event.getSpeedLimit();
    }

    @EventSourcingHandler
    public void on(@NotNull RadarUpdatedEvent event){
        log.info("Handling RadarUpdatedEvent");
        this.radarId = event.getEventId();
        this.address = event.getAddress();
        this.latitude = event.getLatitude();
        this.longitude = event.getLongitude();
        this.speedLimit = event.getSpeedLimit();
    }

    @EventSourcingHandler
    public void on(@NotNull RadarDeletedEvent event){
        log.info("Handling RadarDeletedEvent");
        this.radarId = event.getEventId();
        this.address = null;
        this.latitude = null;
        this.longitude = null;
        this.speedLimit = null;
    }

}
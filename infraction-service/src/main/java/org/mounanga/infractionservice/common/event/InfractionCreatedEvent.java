package org.mounanga.infractionservice.common.event;

import lombok.Getter;
import org.mounanga.infractionservice.commands.command.BaseCommand;

import java.time.LocalDateTime;


@Getter
public class InfractionCreatedEvent extends BaseEvent<String> {

    private final LocalDateTime dateTime;

    private final Double speed;

    private final Double amount;

    private final String vehicleId;

    private final String radarId;

    public InfractionCreatedEvent(String id, LocalDateTime dateTime, Double speed, Double amount, String vehicleId, String radarId) {
        super(id);
        this.dateTime = dateTime;
        this.speed = speed;
        this.amount = amount;
        this.vehicleId = vehicleId;
        this.radarId = radarId;
    }
}

package org.mounanga.infractionservice.common.event;

import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class InfractionUpdatedEvent extends BaseEvent<String> {

    private final LocalDateTime dateTime;

    private final Double speed;

    private final Double amount;

    private final String vehicleId;

    private final String radarId;

    public InfractionUpdatedEvent(String id, LocalDateTime dateTime, Double speed, Double amount, String vehicleId, String radarId) {
        super(id);
        this.dateTime = dateTime;
        this.speed = speed;
        this.amount = amount;
        this.vehicleId = vehicleId;
        this.radarId = radarId;
    }
}

package org.mounanga.radarservice.common.event;

import lombok.Getter;

@Getter
public class RadarCreatedEvent extends BaseEvent<String> {

    private final String address;
    private final Double latitude;
    private final Double longitude;
    private final Double speedLimit;

    public RadarCreatedEvent(String eventId, String address, Double latitude, Double longitude, Double speedLimit) {
        super(eventId);
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speedLimit = speedLimit;
    }
}

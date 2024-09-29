package org.mounanga.radarqueryservice.event;

import lombok.Getter;

@Getter
public class RadarUpdatedEvent extends BaseEvent<String> {

    private final String address;
    private final Double latitude;
    private final Double longitude;
    private final Double speedLimit;

    public RadarUpdatedEvent(String eventId, String address, Double latitude, Double longitude, Double speedLimit) {
        super(eventId);
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speedLimit = speedLimit;
    }
}

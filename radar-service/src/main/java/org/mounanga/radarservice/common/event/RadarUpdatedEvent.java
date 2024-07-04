package org.mounanga.radarservice.common.event;


import lombok.Getter;

@Getter
public class RadarUpdatedEvent extends BaseEvent<String> {
    private final Double speedLimit;

    private final String address;

    private final Double longitude;

    private final Double latitude;

    public RadarUpdatedEvent(String id, Double speedLimit, String address, Double longitude, Double latitude) {
        super(id);
        this.speedLimit = speedLimit;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}

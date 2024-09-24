package org.mounanga.registrationservice.common.event.vehicle;

import lombok.Getter;
import org.mounanga.registrationservice.common.event.BaseEvent;

@Getter
public class VehicleUpdatedEvent extends BaseEvent<String> {

    private final String registrationId;
    private final String marque;
    private final String model;
    private final Double taxPower;
    private final String description;
    private final String ownerId;

    public VehicleUpdatedEvent(String eventId, String registrationId, String marque, String model, Double taxPower, String description, String ownerId) {
        super(eventId);
        this.registrationId = registrationId;
        this.marque = marque;
        this.model = model;
        this.taxPower = taxPower;
        this.description = description;
        this.ownerId = ownerId;
    }
}

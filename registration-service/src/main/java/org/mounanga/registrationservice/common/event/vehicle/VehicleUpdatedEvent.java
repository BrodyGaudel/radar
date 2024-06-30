package org.mounanga.registrationservice.common.event.vehicle;

import lombok.Getter;
import org.mounanga.registrationservice.common.event.BaseEvent;

@Getter
public class VehicleUpdatedEvent extends BaseEvent<String> {

    private final String registrationId;
    private final String marque;
    private final String model;
    private final String description;
    private final Double taxPower;
    private final String ownerId;

    public VehicleUpdatedEvent(String id, String registrationId, String marque, String model, String description, Double taxPower, String ownerId) {
        super(id);
        this.registrationId = registrationId;
        this.marque = marque;
        this.model = model;
        this.description = description;
        this.taxPower = taxPower;
        this.ownerId = ownerId;
    }
}

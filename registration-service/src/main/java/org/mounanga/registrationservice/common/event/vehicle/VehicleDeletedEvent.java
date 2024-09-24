package org.mounanga.registrationservice.common.event.vehicle;

import org.mounanga.registrationservice.common.event.BaseEvent;

public class VehicleDeletedEvent extends BaseEvent<String> {

    public VehicleDeletedEvent(String eventId) {
        super(eventId);
    }
}

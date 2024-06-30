package org.mounanga.registrationservice.common.event.vehicle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mounanga.registrationservice.common.event.BaseEvent;

public class VehicleDeletedEvent extends BaseEvent<String> {

    @JsonCreator
    public VehicleDeletedEvent(@JsonProperty("id") String id) {
        super(id);
    }

}

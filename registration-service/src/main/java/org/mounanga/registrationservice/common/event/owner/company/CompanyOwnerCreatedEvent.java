package org.mounanga.registrationservice.common.event.owner.company;

import lombok.Getter;
import org.mounanga.registrationservice.common.event.BaseEvent;

@Getter
public class CompanyOwnerCreatedEvent extends BaseEvent<String> {

    private final String siret;
    private final String name;
    private final String description;
    private final String address;

    public CompanyOwnerCreatedEvent(String eventId, String siret, String name, String description, String address) {
        super(eventId);
        this.siret = siret;
        this.name = name;
        this.description = description;
        this.address = address;
    }
}

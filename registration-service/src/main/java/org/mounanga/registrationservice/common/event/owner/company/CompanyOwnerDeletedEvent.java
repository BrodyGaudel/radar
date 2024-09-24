package org.mounanga.registrationservice.common.event.owner.company;

import lombok.Getter;
import org.mounanga.registrationservice.common.event.BaseEvent;

@Getter
public class CompanyOwnerDeletedEvent extends BaseEvent<String> {

    public CompanyOwnerDeletedEvent(String eventId) {
        super(eventId);
    }
}

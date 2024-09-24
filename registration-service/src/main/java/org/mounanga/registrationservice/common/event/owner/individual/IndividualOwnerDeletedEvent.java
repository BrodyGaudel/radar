package org.mounanga.registrationservice.common.event.owner.individual;

import org.mounanga.registrationservice.common.event.BaseEvent;

public class IndividualOwnerDeletedEvent extends BaseEvent<String> {
    public IndividualOwnerDeletedEvent(String eventId) {
        super(eventId);
    }
}

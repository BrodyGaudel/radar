package org.mounanga.registrationservice.common.event.owner;

import org.mounanga.registrationservice.common.event.BaseEvent;

public class OwnerDeletedEvent extends BaseEvent<String> {

    public OwnerDeletedEvent(String id) {
        super(id);
    }
}

package org.mounanga.infractionservice.common.event;


public class InfractionDeletedEvent extends BaseEvent<String> {

    public InfractionDeletedEvent(String id) {
        super(id);
    }
}

package org.mounanga.radarqueryservice.event;

import lombok.Getter;

@Getter
public class BaseEvent<T> {

    private final T eventId;

    public BaseEvent(T eventId) {
        this.eventId = eventId;
    }

    public BaseEvent() {
        this.eventId = null;
    }
}

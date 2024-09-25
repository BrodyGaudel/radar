package org.mounanga.radarservice.common.event;

import lombok.Getter;

@Getter
public class BaseEvent<T> {

    private final T eventId;

    public BaseEvent(T eventId) {
        this.eventId = eventId;
    }
}

package org.mounanga.radarqueryservice.event;

public class RadarDeletedEvent extends BaseEvent<String> {

    public RadarDeletedEvent(String eventId) {
        super(eventId);
    }
}

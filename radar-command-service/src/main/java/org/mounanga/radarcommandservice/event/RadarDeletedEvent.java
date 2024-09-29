package org.mounanga.radarcommandservice.event;

public class RadarDeletedEvent extends BaseEvent<String> {

    public RadarDeletedEvent(String eventId) {
        super(eventId);
    }
}

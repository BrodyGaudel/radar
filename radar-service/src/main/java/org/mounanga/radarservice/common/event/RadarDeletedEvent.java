package org.mounanga.radarservice.common.event;

public class RadarDeletedEvent extends BaseEvent<String>{
    public RadarDeletedEvent(String id) {
        super(id);
    }

}

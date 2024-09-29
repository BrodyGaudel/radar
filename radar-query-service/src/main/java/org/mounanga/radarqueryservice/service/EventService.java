package org.mounanga.radarqueryservice.service;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.mounanga.radarqueryservice.entity.Radar;
import org.mounanga.radarqueryservice.event.RadarCreatedEvent;
import org.mounanga.radarqueryservice.event.RadarDeletedEvent;
import org.mounanga.radarqueryservice.event.RadarUpdatedEvent;
import org.mounanga.radarqueryservice.exception.ResourceNotFoundException;
import org.mounanga.radarqueryservice.repository.RadarRepository;
import org.mounanga.radarqueryservice.util.factory.EntityFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class EventService {

    private final RadarRepository radarRepository;

    public EventService(RadarRepository radarRepository) {
        this.radarRepository = radarRepository;
    }

    public void on(RadarCreatedEvent event){
        log.info("Received RadarCreatedEvent");
        Radar radar = EntityFactory.create(event);
        Radar radarSaved = radarRepository.save(radar);
        log.info("Saved Radar with ID: {}", radarSaved.getId());
    }

    public void on(@NotNull RadarUpdatedEvent event){
        log.info("Received RadarUpdatedEvent");
        Radar existingRadar = findRadarById(event.getEventId());
        Radar radar = EntityFactory.update(event, existingRadar);
        Radar radarSaved = radarRepository.save(radar);
        log.info("Radar with ID: {} Updated", radarSaved.getId());
    }

    public void on(@NotNull RadarDeletedEvent event){
        log.info("Received RadarDeletedEvent");
        radarRepository.deleteById(event.getEventId());
        log.info("Radar with ID: {} Deleted", event.getEventId());
    }

    private Radar findRadarById(String id){
        return radarRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Radar with ID: " + id + " not found"));
    }
}

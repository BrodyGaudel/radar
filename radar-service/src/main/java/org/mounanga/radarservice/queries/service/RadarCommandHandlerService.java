package org.mounanga.radarservice.queries.service;


import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.radarservice.common.event.RadarCreatedEvent;
import org.mounanga.radarservice.common.event.RadarDeletedEvent;
import org.mounanga.radarservice.common.event.RadarUpdatedEvent;
import org.mounanga.radarservice.queries.entity.Radar;
import org.mounanga.radarservice.queries.exception.ResourceNotFoundException;
import org.mounanga.radarservice.queries.repository.RadarRepository;
import org.mounanga.radarservice.queries.util.EntityFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class RadarCommandHandlerService {

    private final RadarRepository radarRepository;

    public RadarCommandHandlerService(RadarRepository radarRepository) {
        this.radarRepository = radarRepository;
    }

    @EventHandler
    public void on(RadarCreatedEvent event){
        log.info("Received RadarCreatedEvent");
        Radar radar = EntityFactory.create(event);
        Radar radarSaved = radarRepository.save(radar);
        log.info("Saved Radar with ID: {}", radarSaved.getId());
    }

    @EventHandler
    public void on(@NotNull RadarUpdatedEvent event){
        log.info("Received RadarUpdatedEvent");
        Radar existingRadar = findRadarById(event.getEventId());
        Radar radar = EntityFactory.update(event, existingRadar);
        Radar radarSaved = radarRepository.save(radar);
        log.info("Radar with ID: {} Updated", radarSaved.getId());
    }

    @EventHandler
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

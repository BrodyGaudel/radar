package org.mounanga.radarservice.queries.service;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.radarservice.common.event.RadarCreatedEvent;
import org.mounanga.radarservice.common.event.RadarDeletedEvent;
import org.mounanga.radarservice.common.event.RadarUpdatedEvent;
import org.mounanga.radarservice.queries.entity.Radar;
import org.mounanga.radarservice.common.exception.RadarNotFoundException;
import org.mounanga.radarservice.queries.repository.RadarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
public class RadarEventHandlerService {

    private final RadarRepository radarRepository;

    public RadarEventHandlerService(RadarRepository radarRepository) {
        this.radarRepository = radarRepository;
    }

    @EventHandler
    public Radar on(@NotNull RadarCreatedEvent event){
        log.info("# RadarCreatedEvent handled");
        Radar radar = new Radar();
        radar.setId(event.getId());
        radar.setAddress(event.getAddress());
        radar.setLatitude(event.getLatitude());
        radar.setLongitude(event.getLongitude());
        radar.setSpeedLimit(event.getSpeedLimit());
        Radar createdRadar = radarRepository.save(radar);
        log.info("# Radar created with id '{}' at '{}' by '{}'", createdRadar.getId(), createdRadar.getCreatedBy(), createdRadar.getCreatedDate());
        return createdRadar;
    }

    @EventHandler
    public Radar on(@NotNull RadarUpdatedEvent event){
        log.info("# RadarUpdatedEvent handled");
        Radar radar = radarRepository.findById(event.getId())
                .orElseThrow( () -> new RadarNotFoundException("radar with id '" + event.getId() + "' not found"));
        radar.setAddress(event.getAddress());
        radar.setLatitude(event.getLatitude());
        radar.setLongitude(event.getLongitude());
        radar.setSpeedLimit(event.getSpeedLimit());
        Radar updatedRadar = radarRepository.save(radar);
        log.info("# Radar with id '{}' has been updated at '{}' by '{}'", updatedRadar.getId(), updatedRadar.getLastModifiedDate(), updatedRadar.getLastModifiedBy());
        return updatedRadar;
    }

    @EventHandler
    public void on(@NotNull RadarDeletedEvent event){
        log.info("# RadarDeletedEvent handled");
        radarRepository.deleteById(event.getId());
        log.info("# Radar with id '{}' has been deleted", event.getId());
    }


}

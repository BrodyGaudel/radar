package org.mounanga.radarcommandservice.service;


import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.radarcommandservice.event.RadarCreatedEvent;
import org.mounanga.radarcommandservice.event.RadarDeletedEvent;
import org.mounanga.radarcommandservice.event.RadarUpdatedEvent;
import org.mounanga.radarcommandservice.exception.PublisherException;
import org.mounanga.radarcommandservice.util.publisher.EventRestClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class RadarCommandHandlerService {

    private final EventRestClient eventRestClient;

    public RadarCommandHandlerService(EventRestClient eventRestClient) {
        this.eventRestClient = eventRestClient;
    }

    @EventHandler
    public void on(RadarCreatedEvent event){
        log.info("Received RadarCreatedEvent");
        try {
            eventRestClient.sendRadarCreatedEvent(event);
            log.info("Successfully sent RadarCreatedEvent");
        }catch (Exception e){
            throw new PublisherException("Failed to send RadarCreatedEvent", e);
        }
    }

    @EventHandler
    public void on(@NotNull RadarUpdatedEvent event){
        log.info("Received RadarUpdatedEvent");
        try {
            eventRestClient.sendRadarUpdatedEvent(event);
            log.info("Successfully sent RadarUpdatedEvent");
        }catch (Exception e){
            throw new PublisherException("Failed to send RadarUpdatedEvent", e);
        }
    }

    @EventHandler
    public void on(@NotNull RadarDeletedEvent event){
        log.info("Received RadarDeletedEvent");
        try {
            eventRestClient.sendRadarDeletedEvent(event);
            log.info("Successfully sent RadarDeletedEvent");
        }catch (Exception e){
            throw new PublisherException("Failed to send RadarDeletedEvent", e);
        }
    }

}

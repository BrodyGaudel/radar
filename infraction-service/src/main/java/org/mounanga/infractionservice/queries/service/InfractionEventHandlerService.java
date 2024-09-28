package org.mounanga.infractionservice.queries.service;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.infractionservice.common.event.InfractionCreatedEvent;
import org.mounanga.infractionservice.common.event.InfractionDeletedEvent;
import org.mounanga.infractionservice.common.event.InfractionUpdatedEvent;
import org.mounanga.infractionservice.queries.entity.Infraction;
import org.mounanga.infractionservice.queries.exception.ResourceNotFoundException;
import org.mounanga.infractionservice.queries.repository.InfractionRepository;
import org.mounanga.infractionservice.queries.util.EntityFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
public class InfractionEventHandlerService {

    private final InfractionRepository infractionRepository;

    public InfractionEventHandlerService(InfractionRepository infractionRepository) {
        this.infractionRepository = infractionRepository;
    }

    @EventHandler
    public void on(InfractionCreatedEvent event){
        log.info("InfractionCreatedEvent Received");
        Infraction infraction = EntityFactory.create(event);
        Infraction infractionSaved = infractionRepository.save(infraction);
        log.info("Infraction saved with id {}", infractionSaved.getId());
    }

    @EventHandler
    public void on(@NotNull InfractionUpdatedEvent event) {
        log.info("InfractionUpdatedEvent Received");
        Infraction existingInfraction = infractionRepository.findById(event.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Could not find infraction with id " + event.getId()));

        Infraction infraction = EntityFactory.update(event, existingInfraction);

        Infraction infractionSaved = infractionRepository.save(infraction);
        log.info("Infraction with id {} updated", infractionSaved.getId());
    }

    @EventHandler
    public void on(@NotNull InfractionDeletedEvent event){
        log.info("InfractionDeletedEvent Received");
        infractionRepository.deleteById(event.getId());
        log.info("Infraction with id {} deleted", event.getId());
    }
}

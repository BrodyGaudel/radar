package org.mounanga.registrationservice.queries.service;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.registrationservice.common.event.owner.OwnerCreatedEvent;
import org.mounanga.registrationservice.common.event.owner.OwnerDeletedEvent;
import org.mounanga.registrationservice.common.event.owner.OwnerUpdatedEvent;
import org.mounanga.registrationservice.common.exception.ResourceNotFoundException;
import org.mounanga.registrationservice.queries.entity.Owner;
import org.mounanga.registrationservice.queries.repository.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class OwnerEventHandlerService {

    private final OwnerRepository ownerRepository;

    public OwnerEventHandlerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @EventHandler
    public Owner on(@NotNull OwnerCreatedEvent event){
        log.info("# OwnerCreatedEvent Received");
        Owner owner = Owner.builder()
                .id(event.getId())
                .firstname(event.getFirstname())
                .lastname(event.getLastname())
                .dateOfBirth(event.getDateOfBirth())
                .placeOfBirth(event.getPlaceOfBirth())
                .gender(event.getGender())
                .cin(event.getCin())
                .nationality(event.getNationality())
                .build();
        Owner ownerSaved = ownerRepository.save(owner);
        log.info("# Owner saved with id '{}' at '{}' by '{}'.", ownerSaved.getId(), ownerSaved.getCreatedDate(), ownerSaved.getCreatedBy());
        return ownerSaved;
    }

    @EventHandler
    public Owner on(@NotNull OwnerUpdatedEvent event){
        log.info("# OwnerUpdatedEvent Received");
        Owner owner = ownerRepository.findById(event.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Owner with id '"+event.getId()+" 'not found"));
        owner.setId(event.getId());
        owner.setFirstname(event.getFirstname());
        owner.setLastname(event.getLastname());
        owner.setDateOfBirth(event.getDateOfBirth());
        owner.setPlaceOfBirth(event.getPlaceOfBirth());
        owner.setGender(event.getGender());
        owner.setCin(event.getCin());
        owner.setNationality(event.getNationality());
        Owner ownerUpdated = ownerRepository.save(owner);
        log.info("# owner with id '{}' updated at '{}' by '{}'", ownerUpdated.getId(), ownerUpdated.getLastModifiedDate(), ownerUpdated.getLastModifiedBy()) ;
        return ownerUpdated;
    }

    @EventHandler
    public void on(@NotNull OwnerDeletedEvent event){
        log.info("# OwnerDeletedEvent Received");
        try{
            ownerRepository.deleteById(event.getId());
            log.info("# owner with id '{}' deleted.", event.getId());
        }catch (Exception e){
            log.error("# owner with id '{}' could not be deleted. cause: {}", event.getId(), e.getMessage());
        }
    }
}

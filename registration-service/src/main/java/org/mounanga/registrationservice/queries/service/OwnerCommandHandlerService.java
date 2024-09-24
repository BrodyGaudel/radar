package org.mounanga.registrationservice.queries.service;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.registrationservice.common.event.owner.company.CompanyOwnerCreatedEvent;
import org.mounanga.registrationservice.common.event.owner.company.CompanyOwnerDeletedEvent;
import org.mounanga.registrationservice.common.event.owner.company.CompanyOwnerUpdatedEvent;
import org.mounanga.registrationservice.common.event.owner.individual.IndividualOwnerCreatedEvent;
import org.mounanga.registrationservice.common.event.owner.individual.IndividualOwnerDeletedEvent;
import org.mounanga.registrationservice.common.event.owner.individual.IndividualOwnerUpdatedEvent;
import org.mounanga.registrationservice.queries.entity.CompanyOwner;
import org.mounanga.registrationservice.queries.entity.IndividualOwner;
import org.mounanga.registrationservice.queries.entity.Owner;
import org.mounanga.registrationservice.queries.exception.ResourceNotFoundException;
import org.mounanga.registrationservice.queries.repository.OwnerRepository;
import org.mounanga.registrationservice.queries.util.EntityFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Slf4j
@Service
public class OwnerCommandHandlerService {

    private final OwnerRepository ownerRepository;

    public OwnerCommandHandlerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @EventHandler
    public void on(IndividualOwnerCreatedEvent event){
        log.info("IndividualOwnerCreatedEvent Received");
        IndividualOwner owner = EntityFactory.create(event);
        Owner savedOwner = ownerRepository.save(owner);
        log.info("Individual owner saved with id {}", savedOwner.getId());
    }

    @EventHandler
    public void on(CompanyOwnerCreatedEvent event){
        log.info("CompanyOwnerCreatedEvent Received");
        CompanyOwner owner = EntityFactory.create(event);
        Owner savedOwner = ownerRepository.save(owner);
        log.info("CompanyOwner saved with id {}", savedOwner.getId());
    }

    @EventHandler
    public void on(@NotNull IndividualOwnerUpdatedEvent event){
        log.info("IndividualOwnerUpdatedEvent Received");
        IndividualOwner ownerFound = findIndividualOwnerById(event.getEventId());
        IndividualOwner owner = EntityFactory.update(event, ownerFound);
        Owner updatedOwner = ownerRepository.save(owner);
        log.info("Individual owner with id {} updated", updatedOwner.getId());
    }

    @EventHandler
    public void on(@NotNull CompanyOwnerUpdatedEvent event){
        log.info("CompanyOwnerUpdatedEvent Received");
        CompanyOwner ownerFound = findCompanyOwnerById(event.getEventId());
        CompanyOwner owner = EntityFactory.update(event, ownerFound);
        Owner updatedOwner = ownerRepository.save(owner);
        log.info("CompanyOwner with id {} updated", updatedOwner.getId());
    }

    @EventHandler
    public void on(@NotNull IndividualOwnerDeletedEvent event){
        log.info("IndividualOwnerDeletedEvent Received");
        IndividualOwner ownerFound = findIndividualOwnerById(event.getEventId());
        ownerRepository.delete(ownerFound);
        log.info("Individual owner with id {} deleted", ownerFound.getId());
    }

    @EventHandler
    public void on(@NotNull CompanyOwnerDeletedEvent event){
        log.info("CompanyOwnerDeletedEvent Received");
        CompanyOwner ownerFound = findCompanyOwnerById(event.getEventId());
        ownerRepository.delete(ownerFound);
        log.info("Company owner with id {} deleted", ownerFound.getId());
    }


    private IndividualOwner findIndividualOwnerById(String id){
        Owner owner = findOwnerById(id);
        if(owner instanceof IndividualOwner individualOwner) {
            return individualOwner;
        }else{
            throw new ResourceNotFoundException("Could not find individual owner with id " + id);
        }
    }

    private CompanyOwner findCompanyOwnerById(String id){
        Owner owner = findOwnerById(id);
        if(owner instanceof CompanyOwner companyOwner) {
            return companyOwner;
        }else{
            throw new ResourceNotFoundException("Could not find company owner with id " + id);
        }
    }

    private Owner findOwnerById(String id){
        return ownerRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Owner with id " + id + " not found"));
    }
}

package org.mounanga.registrationservice.queries.service;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.registrationservice.common.event.vehicle.VehicleCreatedEvent;
import org.mounanga.registrationservice.common.event.vehicle.VehicleDeletedEvent;
import org.mounanga.registrationservice.common.event.vehicle.VehicleUpdatedEvent;
import org.mounanga.registrationservice.common.exception.ResourceNotFoundException;
import org.mounanga.registrationservice.queries.entity.Owner;
import org.mounanga.registrationservice.queries.entity.Vehicle;
import org.mounanga.registrationservice.queries.repository.OwnerRepository;
import org.mounanga.registrationservice.queries.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class VehicleEventHandlerService {

    private final VehicleRepository vehicleRepository;
    private final OwnerRepository ownerRepository;

    public VehicleEventHandlerService(VehicleRepository vehicleRepository, OwnerRepository ownerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.ownerRepository = ownerRepository;
    }

    @EventHandler
    public Vehicle on(@NotNull VehicleCreatedEvent event){
        log.info("VehicleCreatedEvent Received");
        Owner owner = ownerRepository.findById(event.getOwnerId())
                .orElseThrow( () -> new ResourceNotFoundException("Owner with id '"+event.getId()+"' not found."));
        Vehicle vehicle = Vehicle.builder()
                .id(event.getId())
                .model(event.getModel())
                .description(event.getDescription())
                .marque(event.getMarque())
                .registrationId(event.getRegistrationId())
                .taxPower(event.getTaxPower())
                .owner(owner)
                .build();
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        log.info("Vehicle saved with id '{}', by '{}' at '{}'", savedVehicle.getId(), savedVehicle.getCreateBy(), savedVehicle.getCreatedDate());
        return savedVehicle;
    }

    @EventHandler
    public Vehicle on(@NotNull VehicleUpdatedEvent event){
        log.info("VehicleUpdatedEvent Received");
        Vehicle vehicle = vehicleRepository.findById(event.getId())
                .orElseThrow( () -> new ResourceNotFoundException("Vehicle with id '"+event.getId()+"' not found"));
        vehicle.setDescription(event.getDescription());
        vehicle.setMarque(event.getMarque());
        vehicle.setRegistrationId(event.getRegistrationId());
        vehicle.setTaxPower(event.getTaxPower());
        vehicle.setModel(event.getModel());
        if(!vehicle.getOwner().getId().equals(event.getOwnerId())){
            Owner owner = ownerRepository.findById(event.getId())
                    .orElseThrow( () -> new ResourceNotFoundException("Owner with id '"+event.getId()+"' not found"));
            vehicle.setOwner(owner);
        }
        Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        log.info("Vehicle with id '{}' updated at '{}' by '{}'", updatedVehicle.getId(), updatedVehicle.getLastModifiedDate(), updatedVehicle.getLastModifiedBy());
        return updatedVehicle;
    }

    @EventHandler
    public void on(@NotNull VehicleDeletedEvent event){
        log.info("VehicleDeletedEvent Received");
        try{
            vehicleRepository.deleteById(event.getId());
            log.info("Vehicle with id '{}' deleted", event.getId());
        }catch (Exception e){
            log.error("Vehicle with id '{}' could not be deleted : '{}'", event.getId(), e.getMessage());
        }
    }
}

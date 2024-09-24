package org.mounanga.registrationservice.queries.service;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.registrationservice.common.event.vehicle.VehicleCreatedEvent;
import org.mounanga.registrationservice.common.event.vehicle.VehicleDeletedEvent;
import org.mounanga.registrationservice.common.event.vehicle.VehicleUpdatedEvent;
import org.mounanga.registrationservice.queries.entity.Owner;
import org.mounanga.registrationservice.queries.entity.Vehicle;
import org.mounanga.registrationservice.queries.exception.ResourceNotFoundException;
import org.mounanga.registrationservice.queries.repository.OwnerRepository;
import org.mounanga.registrationservice.queries.repository.VehicleRepository;
import org.mounanga.registrationservice.queries.util.EntityFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class VehicleCommandHandlerService {

    private final VehicleRepository vehicleRepository;
    private final OwnerRepository ownerRepository;

    public VehicleCommandHandlerService(VehicleRepository vehicleRepository, OwnerRepository ownerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.ownerRepository = ownerRepository;
    }

    @EventHandler
    public void on(@NotNull VehicleCreatedEvent event){
        log.info("VehicleCreatedEvent received");
        Owner owner = findOwnerById(event.getOwnerId());
        Vehicle vehicle = EntityFactory.create(event, owner);
        Vehicle persistedVehicle = vehicleRepository.save(vehicle);
        log.info("Vehicle saved with id  {}", persistedVehicle.getId());
    }

    @EventHandler
    public void on(@NotNull VehicleUpdatedEvent event){
        log.info("VehicleUpdatedEvent received");
        Vehicle vehicleFound = findVehicleById(event.getEventId());
        Owner owner = findOwnerById(event.getOwnerId());
        Vehicle vehicle = EntityFactory.update(event, owner, vehicleFound);
        Vehicle persistedVehicle = vehicleRepository.save(vehicle);
        log.info("Vehicle with id  {} updated", persistedVehicle.getId());
    }

    @EventHandler
    public void on(@NotNull VehicleDeletedEvent event){
        log.info("VehicleDeletedEvent received");
        Vehicle vehicle = findVehicleById(event.getEventId());
        vehicleRepository.delete(vehicle);
        log.info("Vehicle with id  {} deleted", vehicle.getId());
    }

    private Owner findOwnerById(String id){
        return ownerRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Owner with id " + id + " not found"));
    }

    private Vehicle findVehicleById(String id){
        return vehicleRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Vehicle with id " + id + " not found"));
    }
}

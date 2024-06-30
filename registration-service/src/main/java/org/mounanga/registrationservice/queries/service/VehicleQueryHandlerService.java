package org.mounanga.registrationservice.queries.service;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.registrationservice.queries.dto.VehicleResponseDTO;
import org.mounanga.registrationservice.queries.entity.Vehicle;
import org.mounanga.registrationservice.queries.query.*;
import org.mounanga.registrationservice.queries.repository.VehicleRepository;
import org.mounanga.registrationservice.queries.util.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VehicleQueryHandlerService {

    private final VehicleRepository vehicleRepository;

    public VehicleQueryHandlerService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @QueryHandler
    public VehicleResponseDTO handle(@NotNull GetVehicleByIdQuery query){
        log.info("GetVehicleByIdQuery handled");
        Vehicle vehicle = vehicleRepository.findById(query.getId()).orElse(null);
        if(vehicle == null){
            log.warn("Vehicle with id '{}' not found.", query.getId());
            return null;
        }
        log.info("Vehicle with id '{}' found.", vehicle.getId());
        return Mappers.fromVehicle(vehicle);
    }

    @QueryHandler
    public VehicleResponseDTO handle(@NotNull GetVehicleByRegistrationIdQuery query){
        log.info("GetVehicleByRegisterIdQuery  handled");
        Vehicle vehicle = vehicleRepository.findByRegistrationId(query.getRegisterId()).orElse(null);
        if(vehicle == null){
            log.warn("Vehicle with registerId '{}' not found.", query.getRegisterId());
            return null;
        }
        log.info("Vehicle with registerId '{}' found.", vehicle.getId());
        return Mappers.fromVehicle(vehicle);
    }

    @QueryHandler
    public List<VehicleResponseDTO> handle(@NotNull GetVehiclesByOwnerIdQuery query){
        log.info("GetVehiclesByOwnerIdQuery handled");
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        Page<Vehicle> vehiclePage = vehicleRepository.findByOwnerId(query.getOrderId(), pageable);
        log.info("'{}' vehicles found.", vehiclePage.getTotalElements());
        return vehiclePage.getContent().stream().map(Mappers::fromVehicle).toList();
    }

    @QueryHandler
    public List<VehicleResponseDTO> handle(@NotNull SearchVehiclesQuery query){
        log.info("SearchOwnerQuery handled");
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        String keyword = "%"+query.getKeyword()+"%";
        Page<Vehicle> vehiclePage = vehicleRepository.search(keyword, pageable);
        log.info("'{}' vehicles found", vehiclePage.getTotalElements());
        return vehiclePage.getContent().stream().map(Mappers::fromVehicle).toList();
    }

}

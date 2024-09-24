package org.mounanga.registrationservice.queries.service;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.mounanga.registrationservice.queries.dto.VehicleResponseDTO;
import org.mounanga.registrationservice.queries.entity.Vehicle;
import org.mounanga.registrationservice.queries.query.vehicle.GetVehicleByIdQuery;
import org.mounanga.registrationservice.queries.query.vehicle.GetVehicleByKeywordQuery;
import org.mounanga.registrationservice.queries.query.vehicle.GetVehicleByOwnerIdQuery;
import org.mounanga.registrationservice.queries.query.vehicle.GetVehicleByRegistrationIdQuery;
import org.mounanga.registrationservice.queries.repository.VehicleRepository;
import org.mounanga.registrationservice.queries.util.DtoFactory;
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
    public VehicleResponseDTO handle(GetVehicleByIdQuery query){
        log.info("Handling GetVehicleByIdQuery: {}", query);
        Vehicle vehicle = vehicleRepository.findById(query.getVehicleId()).orElse(null);
        if(vehicle == null){
            log.warn("Vehicle with id '{}' not found", query.getVehicleId());
            return null;
        }
        log.info("vehicle found with id: {}", vehicle.getId());
        return DtoFactory.fromVehicle(vehicle);
    }

    @QueryHandler
    public VehicleResponseDTO handle(GetVehicleByRegistrationIdQuery query){
        log.info("Handling GetVehicleByRegistrationIdQuery: {}", query);
        Vehicle vehicle = vehicleRepository.findByRegistrationId(query.getRegistrationId()).orElse(null);
        if(vehicle == null){
            log.warn("Vehicle with registration's id '{}' not found", query.getRegistrationId());
            return null;
        }
        log.info("vehicle found with registration's id : {}", vehicle.getRegistrationId());
        return DtoFactory.fromVehicle(vehicle);
    }

    @QueryHandler
    public List<VehicleResponseDTO> handle(GetVehicleByOwnerIdQuery query){
        log.info("Handling GetVehicleByOwnerIdQuery: {}", query);
        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize());
        List<Vehicle> vehicles = vehicleRepository.findByOwnerId(query.getOwnerId(), pageable).getContent();
        log.info("vehicles found with ownerId: {}", vehicles.size());
        return DtoFactory.fromListOfVehicle(vehicles);
    }

    @QueryHandler
    public List<VehicleResponseDTO> handle(GetVehicleByKeywordQuery query){
        log.info("Handling GetVehicleByKeywordQuery: {}", query);
        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize());
        List<Vehicle> vehicles = vehicleRepository.search("%"+query.getKeyword()+"%", pageable).getContent();
        log.info("vehicles found with keyword: {}", query.getKeyword());
        return DtoFactory.fromListOfVehicle(vehicles);
    }
}

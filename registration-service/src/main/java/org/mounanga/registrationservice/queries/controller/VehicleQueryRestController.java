package org.mounanga.registrationservice.queries.controller;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.mounanga.registrationservice.common.exception.ResourceNotFoundException;
import org.mounanga.registrationservice.queries.dto.VehicleResponseDTO;
import org.mounanga.registrationservice.queries.query.GetVehicleByIdQuery;
import org.mounanga.registrationservice.queries.query.GetVehicleByRegistrationIdQuery;
import org.mounanga.registrationservice.queries.query.GetVehiclesByOwnerIdQuery;
import org.mounanga.registrationservice.queries.query.SearchOwnerQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/queries/vehicles")
@Slf4j
public class VehicleQueryRestController {

    private final QueryGateway queryGateway;

    public VehicleQueryRestController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/get/{id}")
    public VehicleResponseDTO getVehicleById(@PathVariable String id) {
        GetVehicleByIdQuery query = new GetVehicleByIdQuery(id);
        ResponseType<VehicleResponseDTO> responseType = ResponseTypes.instanceOf(VehicleResponseDTO.class);
        VehicleResponseDTO vehicle = queryGateway.query(query, responseType).join();
        if(vehicle == null) {
            throw new ResourceNotFoundException(String.format("Vehicle with id '%s' not found.", id));
        }
        return vehicle;
    }

    @GetMapping("/find/{registerId}")
    public VehicleResponseDTO getVehicleByRegisterId(@PathVariable String registerId) {
        GetVehicleByRegistrationIdQuery query = new GetVehicleByRegistrationIdQuery(registerId);
        ResponseType<VehicleResponseDTO> responseType = ResponseTypes.instanceOf(VehicleResponseDTO.class);
        VehicleResponseDTO vehicle = queryGateway.query(query, responseType).join();
        if(vehicle == null) {
            throw new ResourceNotFoundException(String.format("Vehicle with registerId '%s' not found.", registerId));
        }
        return vehicle;
    }

    @GetMapping("/list")
    public List<VehicleResponseDTO> getAllVehiclesByOwnerId(
            @RequestParam(name = "ownerId") String ownerId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        GetVehiclesByOwnerIdQuery query = new GetVehiclesByOwnerIdQuery(ownerId, page, size);
        ResponseType<List<VehicleResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(VehicleResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }

    @GetMapping("/search")
    public List<VehicleResponseDTO> searchVehicles(
            @RequestParam(name = "keyword", defaultValue = " ") String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        SearchOwnerQuery query = new SearchOwnerQuery(keyword, page, size);
        ResponseType<List<VehicleResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(VehicleResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }


}

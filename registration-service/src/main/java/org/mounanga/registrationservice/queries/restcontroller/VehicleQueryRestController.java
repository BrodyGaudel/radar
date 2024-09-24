package org.mounanga.registrationservice.queries.restcontroller;

import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.mounanga.registrationservice.queries.dto.VehicleResponseDTO;
import org.mounanga.registrationservice.queries.exception.ResourceNotFoundException;
import org.mounanga.registrationservice.queries.query.vehicle.GetVehicleByIdQuery;
import org.mounanga.registrationservice.queries.query.vehicle.GetVehicleByKeywordQuery;
import org.mounanga.registrationservice.queries.query.vehicle.GetVehicleByOwnerIdQuery;
import org.mounanga.registrationservice.queries.query.vehicle.GetVehicleByRegistrationIdQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/queries/vehicles")
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
            throw new ResourceNotFoundException(String.format("Vehicle with id '%s' not found", id));
        }
        return vehicle;
    }

    @GetMapping("/find/{registrationId}")
    public VehicleResponseDTO getVehicleByRegistrationId(@PathVariable(name = "registrationId") String registrationId) {
        GetVehicleByRegistrationIdQuery query = new GetVehicleByRegistrationIdQuery(registrationId);
        ResponseType<VehicleResponseDTO> responseType = ResponseTypes.instanceOf(VehicleResponseDTO.class);
        VehicleResponseDTO vehicle = queryGateway.query(query, responseType).join();
        if(vehicle == null) {
            throw new ResourceNotFoundException(String.format("Vehicle with registration's id '%s' not found", registrationId));
        }
        return vehicle;
    }

    @GetMapping("/list")
    public List<VehicleResponseDTO> getVehiclesByOwnerId(@RequestParam(name = "ownerId") String ownerId,
                                                         @RequestParam(name = "page", defaultValue = "0")  int page,
                                                         @RequestParam(name = "size", defaultValue = "9") int size) {

        GetVehicleByOwnerIdQuery query = new GetVehicleByOwnerIdQuery(ownerId, page, size);
        ResponseType<List<VehicleResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(VehicleResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }

    @GetMapping("/search")
    public List<VehicleResponseDTO> getVehiclesByKeyword(@RequestParam(name = "keyword", defaultValue = " ") String keyword,
                                                         @RequestParam(name = "page", defaultValue = "0")  int page,
                                                         @RequestParam(name = "size", defaultValue = "9") int size) {

        GetVehicleByKeywordQuery query = new GetVehicleByKeywordQuery(keyword, page, size);
        ResponseType<List<VehicleResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(VehicleResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }


}

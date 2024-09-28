package org.mounanga.infractionservice.queries.restcontroller;

import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.mounanga.infractionservice.queries.dto.InfractionResponseDTO;
import org.mounanga.infractionservice.queries.exception.ResourceNotFoundException;
import org.mounanga.infractionservice.queries.query.GetInfractionByIdQuery;
import org.mounanga.infractionservice.queries.query.GetInfractionByRadarIdQuery;
import org.mounanga.infractionservice.queries.query.GetInfractionByVehicleIdQuery;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/queries/infractions")
public class InfractionRestController {

    private final QueryGateway queryGateway;

    public InfractionRestController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/get/{id}")
    public InfractionResponseDTO getInfraction(@PathVariable String id) {

        GetInfractionByIdQuery query = new GetInfractionByIdQuery(id);
        ResponseType<InfractionResponseDTO> responseType = ResponseTypes.instanceOf(InfractionResponseDTO.class);
        InfractionResponseDTO infraction = queryGateway.query(query, responseType).join();
        if (infraction == null) {
            throw new ResourceNotFoundException(String.format("Infraction with id %s not found", id));
        }
        return infraction;
    }


    @GetMapping("/list")
    public List<InfractionResponseDTO> getInfractionsByRadarId(
            @RequestParam("radarId") String radarId,
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            @RequestParam("page") int page,
            @RequestParam("size") int size) {

        GetInfractionByRadarIdQuery query = new GetInfractionByRadarIdQuery(radarId, start, end, page, size);
        ResponseType<List<InfractionResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(InfractionResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }

    @GetMapping("/all")
    public List<InfractionResponseDTO> getInfractionsByVehicleId(
            @RequestParam("vehicleId") String vehicleId,
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            @RequestParam("page") int page,
            @RequestParam("size") int size) {

        GetInfractionByVehicleIdQuery query = new GetInfractionByVehicleIdQuery(vehicleId, start, end, page, size);
        ResponseType<List<InfractionResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(InfractionResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }
}

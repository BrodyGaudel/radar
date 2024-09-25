package org.mounanga.radarservice.queries.restcontroller;

import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.mounanga.radarservice.queries.dto.RadarResponseDTO;
import org.mounanga.radarservice.queries.exception.ResourceNotFoundException;
import org.mounanga.radarservice.queries.query.GetRadarByAddressQuery;
import org.mounanga.radarservice.queries.query.GetRadarByIdQuery;
import org.mounanga.radarservice.queries.query.GetRadarByLatitudeAndLongitudeQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/queries/radars")
public class RadarQueryRestController {

    private final QueryGateway queryGateway;

    public RadarQueryRestController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/get/{id}")
    public RadarResponseDTO getRadarById(@PathVariable String id) {
        GetRadarByIdQuery query = new GetRadarByIdQuery(id);
        ResponseType<RadarResponseDTO> responseType = ResponseTypes.instanceOf(RadarResponseDTO.class);
        RadarResponseDTO radar = queryGateway.query(query, responseType).join();
        if(radar == null) {
            throw new ResourceNotFoundException(String.format("Radar with id '%s' not found", id));
        }
        return radar;
    }

    @GetMapping("/all")
    public List<RadarResponseDTO> getRadarsByAddress(@RequestParam(name = "address", defaultValue = " ") String address,
                                                     @RequestParam(name = "page", defaultValue = "0") int page,
                                                     @RequestParam(name = "size", defaultValue = "9") int size) {

        GetRadarByAddressQuery query = new GetRadarByAddressQuery(address, page, size);
        ResponseType<List<RadarResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(RadarResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }

    @GetMapping("/list")
    public List<RadarResponseDTO> getRadarsByLatitudeAndLongitude(@RequestParam(name = "latitude") Double latitude,
                                                                  @RequestParam(name = "longitude") Double longitude,
                                                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                                                  @RequestParam(name = "size", defaultValue = "9") int size) {

        GetRadarByLatitudeAndLongitudeQuery query = new GetRadarByLatitudeAndLongitudeQuery(latitude, longitude, page, size);
        ResponseType<List<RadarResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(RadarResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }
}

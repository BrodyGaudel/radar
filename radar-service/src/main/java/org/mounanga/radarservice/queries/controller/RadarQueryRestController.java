package org.mounanga.radarservice.queries.controller;

import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.mounanga.radarservice.common.exception.RadarNotFoundException;
import org.mounanga.radarservice.queries.dto.RadarResponseDTO;
import org.mounanga.radarservice.queries.query.GetAllRadarsQuery;
import org.mounanga.radarservice.queries.query.GetRadarByAddressDetailsQuery;
import org.mounanga.radarservice.queries.query.GetRadarByIdQuery;
import org.mounanga.radarservice.queries.query.SearchRadarByAddressQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class RadarQueryRestController {

    private final QueryGateway queryGateway;

    public RadarQueryRestController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/get/{id}")
    public RadarResponseDTO getRadarById(@PathVariable String id) {
        GetRadarByIdQuery query = new GetRadarByIdQuery(id);
        ResponseType<RadarResponseDTO> responseType = ResponseTypes.instanceOf(RadarResponseDTO.class);
        RadarResponseDTO dto = queryGateway.query(query, responseType).join();
        if(dto == null) {
            throw new RadarNotFoundException("Radar not found");
        }
        return dto;
    }

    @GetMapping("/list")
    public List<RadarResponseDTO> getAllRadars(@RequestParam(name = "page", defaultValue = "0") int page,
                                               @RequestParam(name = "size", defaultValue = "10") int size) {
        GetAllRadarsQuery query = new GetAllRadarsQuery(page, size);
        ResponseType<List<RadarResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(RadarResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }



    @GetMapping("/details")
    public RadarResponseDTO getRadarByDetails(@RequestParam(name = "latitude") double latitude,
                                              @RequestParam(name = "longitude") double longitude) {

        GetRadarByAddressDetailsQuery query = new GetRadarByAddressDetailsQuery(longitude, latitude);
        ResponseType<RadarResponseDTO> responseType = ResponseTypes.instanceOf(RadarResponseDTO.class);
        RadarResponseDTO dto = queryGateway.query(query, responseType).join();
        if(dto == null) {
            throw new RadarNotFoundException("Radar not found");
        }
        return dto;
    }

    public List<RadarResponseDTO> getRadarByAddress(@RequestParam(name = "address", defaultValue = " ") String address,
                                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                                    @RequestParam(name = "size", defaultValue = "10") int size) {
        SearchRadarByAddressQuery query = new SearchRadarByAddressQuery(address, page, size);
        ResponseType<List<RadarResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(RadarResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }
}

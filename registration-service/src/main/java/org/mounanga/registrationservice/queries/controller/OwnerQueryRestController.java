package org.mounanga.registrationservice.queries.controller;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.mounanga.registrationservice.common.exception.ResourceNotFoundException;
import org.mounanga.registrationservice.queries.dto.OwnerResponseDTO;
import org.mounanga.registrationservice.queries.query.GetAllOwnersQuery;
import org.mounanga.registrationservice.queries.query.GetOwnerByIdQuery;
import org.mounanga.registrationservice.queries.query.SearchOwnerQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/queries/owners")
@Slf4j
public class OwnerQueryRestController {

    private final QueryGateway queryGateway;

    public OwnerQueryRestController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }


    @GetMapping("/get/{id}")
    public OwnerResponseDTO findOwnerById(@PathVariable String id) {
        GetOwnerByIdQuery query = new GetOwnerByIdQuery(id);
        ResponseType<OwnerResponseDTO> responseType = ResponseTypes.instanceOf(OwnerResponseDTO.class);
        OwnerResponseDTO owner = queryGateway.query(query, responseType).join();
        if(owner == null) {
            throw new ResourceNotFoundException(String.format("Owner with id '%s' not found.", id));
        }
        return owner;
    }


    @GetMapping("/lit")
    public List<OwnerResponseDTO> findAllOwners(@RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "10") int size) {
        GetAllOwnersQuery query = new GetAllOwnersQuery(page, size);
        ResponseType<List<OwnerResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(OwnerResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }

    @GetMapping("/search")
    public List<OwnerResponseDTO> searchOwners(@RequestParam(name = "keyword", defaultValue = " ") String keyword,
                                               @RequestParam(name = "page", defaultValue = "0") int page,
                                               @RequestParam(name = "size", defaultValue = "10") int size) {
        SearchOwnerQuery query = new SearchOwnerQuery(keyword, page, size);
        ResponseType<List<OwnerResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(OwnerResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }
}

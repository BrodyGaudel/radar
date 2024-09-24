package org.mounanga.registrationservice.queries.restcontroller;

import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.mounanga.registrationservice.queries.dto.OwnerResponseDTO;
import org.mounanga.registrationservice.queries.exception.ResourceNotFoundException;
import org.mounanga.registrationservice.queries.query.owner.GetOwnerByIdQuery;
import org.mounanga.registrationservice.queries.query.owner.company.GetCompanyOwnerByKeywordQuery;
import org.mounanga.registrationservice.queries.query.owner.company.GetCompanyOwnerBySiretQuery;
import org.mounanga.registrationservice.queries.query.owner.individual.GetIndividualOwnerByKeywordQuery;
import org.mounanga.registrationservice.queries.query.owner.individual.GetIndividualOwnerByNipQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/queries/owners")
public class OwnerQueryRestController {

    private final QueryGateway queryGateway;

    public OwnerQueryRestController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/get/{id}")
    public OwnerResponseDTO getOwnerById(@PathVariable String id) {
        GetOwnerByIdQuery query = new GetOwnerByIdQuery(id);
        ResponseType<OwnerResponseDTO> responseType = ResponseTypes.instanceOf(OwnerResponseDTO.class);
        OwnerResponseDTO owner = queryGateway.query(query, responseType).join();
        if(owner == null) {
            throw new ResourceNotFoundException(String.format("Could not find owner with id %s", id));
        }
        return owner;
    }

    @GetMapping("/find/company/{siret}")
    public OwnerResponseDTO getOwnerBySiret(@PathVariable(name = "siret") String siret) {
        GetCompanyOwnerBySiretQuery query = new GetCompanyOwnerBySiretQuery(siret);
        ResponseType<OwnerResponseDTO> responseType = ResponseTypes.instanceOf(OwnerResponseDTO.class);
        OwnerResponseDTO owner = queryGateway.query(query, responseType).join();
        if(owner == null) {
            throw new ResourceNotFoundException(String.format("Could not find owner with siret %s", siret));
        }
        return owner;
    }

    @GetMapping("/search/company")
    public List<OwnerResponseDTO> searchCompanyOwners(@RequestParam(name = "keyword", defaultValue = " ") String keyword,
                                                      @RequestParam(name = "page", defaultValue = "0")  int page,
                                                      @RequestParam(name = "size", defaultValue = "9") int size) {

        GetCompanyOwnerByKeywordQuery query = new GetCompanyOwnerByKeywordQuery(keyword, page, size);
        ResponseType<List<OwnerResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(OwnerResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }

    @GetMapping("/find/individual/{nip}")
    public OwnerResponseDTO getOwnerByNip(@PathVariable(name = "nip") String nip) {
        GetIndividualOwnerByNipQuery query = new GetIndividualOwnerByNipQuery(nip);
        ResponseType<OwnerResponseDTO> responseType = ResponseTypes.instanceOf(OwnerResponseDTO.class);
        OwnerResponseDTO owner = queryGateway.query(query, responseType).join();
        if(owner == null) {
            throw new ResourceNotFoundException(String.format("Could not find owner with nip %s", nip));
        }
        return owner;
    }

    @GetMapping("/search/individual")
    public List<OwnerResponseDTO> searchIndividualOwners(@RequestParam(name = "keyword", defaultValue = " ") String keyword,
                                                      @RequestParam(name = "page", defaultValue = "0")  int page,
                                                      @RequestParam(name = "size", defaultValue = "9") int size) {

        GetIndividualOwnerByKeywordQuery query = new GetIndividualOwnerByKeywordQuery(keyword, page, size);
        ResponseType<List<OwnerResponseDTO>> responseType = ResponseTypes.multipleInstancesOf(OwnerResponseDTO.class);
        return queryGateway.query(query, responseType).join();
    }
}

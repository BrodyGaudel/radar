package org.mounanga.registrationservice.queries.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mounanga.registrationservice.queries.dto.OwnerResponseDTO;
import org.mounanga.registrationservice.queries.entity.Owner;
import org.mounanga.registrationservice.queries.query.GetAllOwnersQuery;
import org.mounanga.registrationservice.queries.query.GetOwnerByIdQuery;
import org.mounanga.registrationservice.queries.query.SearchOwnerQuery;
import org.mounanga.registrationservice.queries.repository.OwnerRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class OwnerQueryHandlerServiceTest {

    @InjectMocks
    private OwnerQueryHandlerService ownerQueryHandlerService;

    @Mock
    private OwnerRepository ownerRepository;

    @BeforeEach
    void setUp() {
        ownerQueryHandlerService = new OwnerQueryHandlerService(ownerRepository);
    }

    @Test
    void handleGetAllOwnersQuery() {
        GetAllOwnersQuery query = new GetAllOwnersQuery(0,3);
        List<Owner> owners = new ArrayList<>();
        owners.add(Owner.builder().cin("cin1").id("id1").build());
        owners.add(Owner.builder().cin("cin2").id("id2").build());
        owners.add(Owner.builder().cin("cin3").id("id3").build());
        Page<Owner> ownerPage = new PageImpl<>(owners);
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());

        when(ownerRepository.findAll(pageable)).thenReturn(ownerPage);

        List<OwnerResponseDTO> response = ownerQueryHandlerService.handle(query);
        assertNotNull(response);
        assertEquals(3, response.size());
        assertNotNull(response.get(0).getId());
        assertNotNull(response.get(1).getId());
        assertNotNull(response.get(2).getId());
    }

    @Test
    void handleGetOwnerByIdQuery() {
        GetOwnerByIdQuery query = new GetOwnerByIdQuery("id");
        Owner owner = Owner.builder().cin("cin1").id("id").build();
        when(ownerRepository.findById(anyString())).thenReturn(Optional.of(owner));
        OwnerResponseDTO response = ownerQueryHandlerService.handle(query);
        assertNotNull(response);
        assertEquals("id", response.getId());
    }

    @Test
    void handleSearchOwnerQuery() {
        SearchOwnerQuery query = new SearchOwnerQuery("john", 0,1);
        List<Owner> owners = new ArrayList<>();
        String keyword = "%"+query.getKeyword()+"%";
        owners.add(Owner.builder().cin("cin1").firstname("van john").lastname("bob").id("id1").build());
        Page<Owner> ownerPage = new PageImpl<>(owners);
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());
        when(ownerRepository.search(keyword,pageable)).thenReturn(ownerPage);
        List<OwnerResponseDTO> response = ownerQueryHandlerService.handle(query);
        assertNotNull(response);
        assertEquals(1, response.size());
        assertNotNull(response.get(0));
    }
}
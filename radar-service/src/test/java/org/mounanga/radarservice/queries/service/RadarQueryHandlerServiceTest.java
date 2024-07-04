package org.mounanga.radarservice.queries.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mounanga.radarservice.queries.dto.RadarResponseDTO;
import org.mounanga.radarservice.queries.entity.Radar;
import org.mounanga.radarservice.queries.query.GetAllRadarsQuery;
import org.mounanga.radarservice.queries.query.GetRadarByAddressDetailsQuery;
import org.mounanga.radarservice.queries.query.GetRadarByIdQuery;
import org.mounanga.radarservice.queries.query.SearchRadarByAddressQuery;
import org.mounanga.radarservice.queries.repository.RadarRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class RadarQueryHandlerServiceTest {

    @Mock
    private RadarRepository radarRepository;

    @InjectMocks
    private RadarQueryHandlerService radarQueryHandlerService;

    @BeforeEach
    void setUp() {
        radarQueryHandlerService = new RadarQueryHandlerService(radarRepository);
    }

    @Test
    void testHandleGetRadarByIdQuery() {
        String radarId = UUID.randomUUID().toString();
        Radar radar = new Radar(radarId, 100.0, "123 Street", 45.0, 45.0, "User", null, "User", null);

        when(radarRepository.findById(radarId)).thenReturn(Optional.of(radar));

        GetRadarByIdQuery query = new GetRadarByIdQuery(radarId);
        RadarResponseDTO responseDTO = radarQueryHandlerService.handle(query);

        assertNotNull(responseDTO);
        assertEquals(radarId, responseDTO.getId());
        assertEquals("123 Street", responseDTO.getAddress());
    }

    @Test
    void testHandleGetRadarByIdQueryNotFound() {
        String radarId = UUID.randomUUID().toString();

        when(radarRepository.findById(radarId)).thenReturn(Optional.empty());

        GetRadarByIdQuery query = new GetRadarByIdQuery(radarId);
        RadarResponseDTO responseDTO = radarQueryHandlerService.handle(query);

        assertNull(responseDTO);
    }

    @Test
    void testHandleSearchRadarByAddressQuery() {
        String address = "Street";
        Radar radar = new Radar(UUID.randomUUID().toString(), 100.0, "123 Street", 45.0, 45.0, "User", null, "User", null);
        Page<Radar> radarPage = new PageImpl<>(List.of(radar));

        when(radarRepository.findByAddress(any(String.class), any(Pageable.class))).thenReturn(radarPage);

        SearchRadarByAddressQuery query = new SearchRadarByAddressQuery(address, 0, 10);
        List<RadarResponseDTO> responseDTOList = radarQueryHandlerService.handle(query);

        assertNotNull(responseDTOList);
        assertEquals(1, responseDTOList.size());
        assertEquals("123 Street", responseDTOList.get(0).getAddress());
    }

    @Test
    void testHandleGetRadarByAddressDetailsQuery() {
        double latitude = 45.0;
        double longitude = 45.0;
        Radar radar = new Radar(UUID.randomUUID().toString(), 100.0, "123 Street", latitude, longitude, "User", null, "User", null);

        when(radarRepository.findByLatitudeAndLongitude(latitude, longitude)).thenReturn(Optional.of(radar));

        GetRadarByAddressDetailsQuery query = new GetRadarByAddressDetailsQuery(latitude, longitude);
        RadarResponseDTO responseDTO = radarQueryHandlerService.handle(query);

        assertNotNull(responseDTO);
        assertEquals("123 Street", responseDTO.getAddress());
    }

    @Test
    void testHandleGetRadarByAddressDetailsQueryNotFound() {
        double latitude = 45.0;
        double longitude = 45.0;

        when(radarRepository.findByLatitudeAndLongitude(latitude, longitude)).thenReturn(Optional.empty());

        GetRadarByAddressDetailsQuery query = new GetRadarByAddressDetailsQuery(latitude, longitude);
        RadarResponseDTO responseDTO = radarQueryHandlerService.handle(query);

        assertNull(responseDTO);
    }

    @Test
    void testHandleGetAllRadarsQuery() {
        Radar radar = new Radar(UUID.randomUUID().toString(), 100.0, "123 Street", 45.0, 45.0, "User", null, "User", null);
        Page<Radar> radarPage = new PageImpl<>(List.of(radar));

        when(radarRepository.findAll(any(Pageable.class))).thenReturn(radarPage);

        GetAllRadarsQuery query = new GetAllRadarsQuery(0, 10);
        List<RadarResponseDTO> responseDTOList = radarQueryHandlerService.handle(query);

        assertNotNull(responseDTOList);
        assertEquals(1, responseDTOList.size());
        assertEquals("123 Street", responseDTOList.get(0).getAddress());
    }
}
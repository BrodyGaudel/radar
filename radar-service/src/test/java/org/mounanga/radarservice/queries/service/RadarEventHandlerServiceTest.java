package org.mounanga.radarservice.queries.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mounanga.radarservice.common.event.RadarCreatedEvent;
import org.mounanga.radarservice.common.event.RadarDeletedEvent;
import org.mounanga.radarservice.common.event.RadarUpdatedEvent;
import org.mounanga.radarservice.queries.entity.Radar;
import org.mounanga.radarservice.queries.repository.RadarRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class RadarEventHandlerServiceTest {

    @InjectMocks
    private RadarEventHandlerService service;

    @Mock
    private RadarRepository radarRepository;

    @BeforeEach
    void setUp() {
        service = new RadarEventHandlerService(radarRepository);
    }

    @Test
    void onRadarCreatedEvent() {
        RadarCreatedEvent event = new RadarCreatedEvent("id",100.0,"address",23.1,22.1);
        Radar radar = new Radar();
        radar.setId(event.getId());
        radar.setAddress(event.getAddress());
        radar.setLatitude(event.getLatitude());
        radar.setLongitude(event.getLongitude());
        radar.setSpeedLimit(event.getSpeedLimit());

        when(radarRepository.save(any(Radar.class))).thenReturn(radar);

        Radar response = service.on(event);
        assertNotNull(response);
        assertEquals(response.getId(), event.getId());
        assertEquals(response.getAddress(), event.getAddress());
        assertEquals(response.getLatitude(), event.getLatitude());
        assertEquals(response.getLongitude(), event.getLongitude());
        assertEquals(response.getSpeedLimit(), event.getSpeedLimit());
    }

    @Test
    void onRadarUpdatedEvent() {
        RadarUpdatedEvent event = new RadarUpdatedEvent("id",100.0,"address",23.1,22.1);
        Radar radar = new Radar();
        radar.setId(event.getId());
        radar.setAddress("old address");
        radar.setLatitude(0.0);
        radar.setLongitude(0.0);
        radar.setSpeedLimit(0.0);

        Radar updatedRadar = new Radar();
        updatedRadar.setId(event.getId());
        updatedRadar.setAddress(event.getAddress());
        updatedRadar.setLatitude(event.getLatitude());
        updatedRadar.setLongitude(event.getLongitude());
        updatedRadar.setSpeedLimit(event.getSpeedLimit());

        when(radarRepository.findById(anyString())).thenReturn(Optional.of(radar));
        when(radarRepository.save(any(Radar.class))).thenReturn(updatedRadar);

        Radar response = service.on(event);
        assertNotNull(response);
        assertEquals(response.getId(), event.getId());
        assertEquals(response.getAddress(), event.getAddress());
        assertEquals(response.getLatitude(), event.getLatitude());
        assertEquals(response.getLongitude(), event.getLongitude());
        assertEquals(response.getSpeedLimit(), event.getSpeedLimit());
    }

    @Test
    void onRadarDeletedEvent() {
        RadarDeletedEvent event = new RadarDeletedEvent("id");
        doNothing().when(radarRepository).deleteById(anyString());
        service.on(event);
        verify(radarRepository, times(1)).deleteById(anyString());
    }
}
package org.mounanga.registrationservice.queries.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mounanga.registrationservice.common.enums.Gender;
import org.mounanga.registrationservice.common.event.vehicle.VehicleCreatedEvent;
import org.mounanga.registrationservice.common.event.vehicle.VehicleDeletedEvent;
import org.mounanga.registrationservice.common.event.vehicle.VehicleUpdatedEvent;
import org.mounanga.registrationservice.queries.entity.Owner;
import org.mounanga.registrationservice.queries.entity.Vehicle;
import org.mounanga.registrationservice.queries.repository.OwnerRepository;
import org.mounanga.registrationservice.queries.repository.VehicleRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class VehicleEventHandlerServiceTest {

    @InjectMocks
    private VehicleEventHandlerService vehicleEventHandlerService;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private OwnerRepository ownerRepository;

    @BeforeEach
    void setUp() {
        vehicleEventHandlerService = new VehicleEventHandlerService(vehicleRepository, ownerRepository);
    }

    @Test
    void onVehicleCreatedEvent() {
        VehicleCreatedEvent event = new VehicleCreatedEvent(
                "id",
                "registrationId",
                "marque",
                "model",
                "description",
                89.98,
                "ownerId"
        );
        Owner owner = Owner.builder()
                .id("id")
                .cin("123456")
                .firstname("Kim")
                .lastname("Bob")
                .dateOfBirth(LocalDate.of(1994,1,22))
                .placeOfBirth("everywhere")
                .gender(Gender.M)
                .build();

        Vehicle vehicle = Vehicle.builder()
                .id(event.getId())
                .registrationId(event.getRegistrationId())
                .marque(event.getMarque())
                .model(event.getModel())
                .description(event.getDescription())
                .taxPower(event.getTaxPower())
                .build();

        when(ownerRepository.findById(anyString())).thenReturn(Optional.of(owner));
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

        Vehicle response = vehicleEventHandlerService.on(event);
        assertNotNull(response);
        assertEquals(event.getId(), response.getId());
        assertEquals(event.getRegistrationId(), response.getRegistrationId());
        assertEquals(event.getMarque(), response.getMarque());
        assertEquals(event.getModel(), response.getModel());
        assertEquals(event.getDescription(), response.getDescription());
        assertEquals(event.getTaxPower(), response.getTaxPower());
        verify(ownerRepository, times(1)).findById(anyString());
        verify(vehicleRepository, times(1)).save(any(Vehicle.class));
    }


    @Test
    void onVehicleUpdatedEvent(){
        VehicleUpdatedEvent event = new VehicleUpdatedEvent(
                "id",
                "registrationId",
                "marque",
                "model",
                "description",
                89.98,
                "ownerId"
        );
        Owner owner = Owner.builder().id("ownerId").vehicles(new ArrayList<>()).build();
        Vehicle vehicle = Vehicle.builder().id("id").registrationId("123").owner(owner).build();
        Vehicle updateVehicle = Vehicle.builder()
                .id(event.getId())
                .registrationId(event.getRegistrationId())
                .marque(event.getMarque())
                .model(event.getModel())
                .description(event.getDescription())
                .taxPower(event.getTaxPower())
                .owner(owner)
                .build();

        when(ownerRepository.findById(anyString())).thenReturn(Optional.of(owner));
        when(vehicleRepository.findById(anyString())).thenReturn(Optional.of(vehicle));
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(updateVehicle);

        Vehicle response = vehicleEventHandlerService.on(event);
        assertNotNull(response);
        assertEquals(event.getId(), response.getId());
        assertEquals(event.getRegistrationId(), response.getRegistrationId());
        assertEquals(event.getMarque(), response.getMarque());
        assertEquals(event.getModel(), response.getModel());
        assertEquals(event.getDescription(), response.getDescription());
        assertEquals(event.getTaxPower(), response.getTaxPower());
        verify(vehicleRepository, times(1)).findById(anyString());
        verify(vehicleRepository, times(1)).save(any(Vehicle.class));

    }

    @Test
    void onVehicleDeletedEvent(){
        VehicleDeletedEvent event = new VehicleDeletedEvent("id");
        doNothing().when(vehicleRepository).deleteById(anyString());
        vehicleEventHandlerService.on(event);
        verify(vehicleRepository, times(1)).deleteById(anyString());
    }

}
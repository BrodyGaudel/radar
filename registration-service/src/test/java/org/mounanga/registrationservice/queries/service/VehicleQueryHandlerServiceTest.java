package org.mounanga.registrationservice.queries.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mounanga.registrationservice.queries.dto.VehicleResponseDTO;
import org.mounanga.registrationservice.queries.entity.Owner;
import org.mounanga.registrationservice.queries.entity.Vehicle;
import org.mounanga.registrationservice.queries.query.*;
import org.mounanga.registrationservice.queries.repository.VehicleRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class VehicleQueryHandlerServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleQueryHandlerService vehicleQueryHandlerService;

    @BeforeEach
    void setUp() {
        vehicleQueryHandlerService = new VehicleQueryHandlerService(vehicleRepository);
    }


    @Test
    void onGetVehicleByIdQuery() {
        GetVehicleByIdQuery query = new GetVehicleByIdQuery("id");
        Vehicle vehicle = new Vehicle();
        vehicle.setId("id");
        vehicle.setOwner(Owner.builder().id("ownerId").build());
        when(vehicleRepository.findById(anyString())).thenReturn(Optional.of(vehicle));
        VehicleResponseDTO response = vehicleQueryHandlerService.handle(query);
        assertNotNull(response);
        assertEquals(query.getId(), response.getId());
    }

    @Test
    void handleGetVehicleByRegisterIdQuery() {
        GetVehicleByRegistrationIdQuery query = new GetVehicleByRegistrationIdQuery("registrationId");
        Vehicle vehicle = new Vehicle();
        vehicle.setId("id");
        vehicle.setRegistrationId("registrationId");
        vehicle.setOwner(Owner.builder().id("ownerId").build());

        when(vehicleRepository.findByRegistrationId(anyString())).thenReturn(Optional.of(vehicle));
        VehicleResponseDTO response = vehicleQueryHandlerService.handle(query);
        assertNotNull(response);
        assertEquals(query.getRegisterId(), response.getRegistrationId());

    }

    @Test
    void handleGetVehiclesByOwnerIdQuery() {
        GetVehiclesByOwnerIdQuery query = new GetVehiclesByOwnerIdQuery("ownerId", 0, 3);
        Owner owner = Owner.builder().id("ownerId").build();
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(Vehicle.builder().id("id1").owner(owner).build());
        vehicles.add(Vehicle.builder().id("id2").owner(owner).build());
        vehicles.add(Vehicle.builder().id("id3").owner(owner).build());

        Page<Vehicle> vehiclePage = new PageImpl<>(vehicles);
        when(vehicleRepository.findByOwnerId(anyString(), any(PageRequest.class))).thenReturn(vehiclePage);

        List<VehicleResponseDTO> response = vehicleQueryHandlerService.handle(query);
        assertNotNull(response);
        assertEquals(3, response.size());
    }

    @Test
    void handleSearchOwnerQuery() {
        SearchVehiclesQuery query = new SearchVehiclesQuery("Toyota", 0, 2);
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(Vehicle.builder().id("id1").marque("Toyota Yaris").marque("Toyota").build());
        vehicles.add(Vehicle.builder().id("id2").marque("Toyota Tundra").marque("Toyota").build());
        Page<Vehicle> vehiclePage = new PageImpl<>(vehicles);
        when(vehicleRepository.search(anyString(), any(PageRequest.class))).thenReturn(vehiclePage);
        List<VehicleResponseDTO> response = vehicleQueryHandlerService.handle(query);
        assertNotNull(response);
        assertEquals(2, response.size());
    }
}
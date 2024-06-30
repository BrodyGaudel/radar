package org.mounanga.registrationservice.commands.aggregate;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mounanga.registrationservice.commands.command.vehicle.CreateVehicleCommand;
import org.mounanga.registrationservice.commands.command.vehicle.DeleteVehicleCommand;
import org.mounanga.registrationservice.commands.command.vehicle.UpdateVehicleCommand;
import org.mounanga.registrationservice.common.event.vehicle.VehicleCreatedEvent;
import org.mounanga.registrationservice.common.event.vehicle.VehicleDeletedEvent;
import org.mounanga.registrationservice.common.event.vehicle.VehicleUpdatedEvent;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VehicleAggregateTest {

    private AggregateTestFixture<VehicleAggregate> fixture;

    @BeforeEach
    void setUp() {
        fixture = new AggregateTestFixture<>(VehicleAggregate.class);
    }

    @Test
    void testCreateVehicleCommand() {
        String vehicleId = "vehicle-123";
        CreateVehicleCommand command = new CreateVehicleCommand(
                vehicleId,
                "ABC123",
                "Toyota",
                "Corolla",
                "Description",
                100.0,
                "owner-123"
        );

        VehicleCreatedEvent event = new VehicleCreatedEvent(
                vehicleId,
                "ABC123",
                "Toyota",
                "Corolla",
                "Description",
                100.0,
                "owner-123"
        );

        fixture.givenNoPriorActivity()
                .when(command)
                .expectEvents(event);
    }

    @Test
    void testUpdateVehicleCommand() {
        String vehicleId = "vehicle-123";
        String registrationId = "ABC123";

        UpdateVehicleCommand command = new UpdateVehicleCommand(
                vehicleId,
                registrationId,
                "Honda",
                "Civic",
                "Updated Description",
                120.0,
                "owner-123"
        );

        VehicleUpdatedEvent event = new VehicleUpdatedEvent(
                vehicleId,
                registrationId,
                "Honda",
                "Civic",
                "Updated Description",
                120.0,
                "owner-123"
        );

        fixture.given(new VehicleCreatedEvent(
                        vehicleId,
                        registrationId,
                        "Toyota",
                        "Corolla",
                        "Description",
                        100.0,
                        "owner-123"))
                .when(command)
                .expectEvents(event);
    }

    @Test
    void testDeleteVehicleCommand() {
        String vehicleId = "vehicle-123";
        String registrationId = "ABC123";

        DeleteVehicleCommand command = new DeleteVehicleCommand(vehicleId);

        VehicleDeletedEvent event = new VehicleDeletedEvent(vehicleId);

        fixture.given(new VehicleCreatedEvent(
                        vehicleId,
                        registrationId,
                        "Toyota",
                        "Corolla",
                        "Description",
                        100.0,
                        "owner-123"))
                .when(command)
                .expectEvents(event);
    }
}
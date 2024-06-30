package org.mounanga.registrationservice.commands.util.vehicle;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.registrationservice.common.event.vehicle.VehicleCreatedEvent;
import org.mounanga.registrationservice.common.event.vehicle.VehicleDeletedEvent;
import org.mounanga.registrationservice.common.event.vehicle.VehicleUpdatedEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ProcessingGroup("vehicleRegistration")
public class VehicleEventHandler {

    @EventHandler
    public void on(@NotNull VehicleCreatedEvent event, @NotNull VehicleRegistrationRepository repository){
        repository.save(new VehicleRegistration(event.getId(), event.getRegistrationId()));
    }

    @EventHandler
    public void on(@NotNull VehicleUpdatedEvent event, @NotNull VehicleRegistrationRepository repository) {
        Optional<VehicleRegistration> vehicleRegistration = repository.findById(event.getId());
        if (vehicleRegistration.isPresent()) {
            vehicleRegistration.get().setRegistrationId(event.getRegistrationId());
            repository.save(vehicleRegistration.get());
        }
    }

    @EventHandler
    public void on(@NotNull VehicleDeletedEvent event, @NotNull VehicleRegistrationRepository repository) {
        repository.deleteById(event.getId());
    }
}

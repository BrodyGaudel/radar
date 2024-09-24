package org.mounanga.registrationservice.commands.util.validation.vehicle;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.registrationservice.common.event.vehicle.VehicleCreatedEvent;
import org.mounanga.registrationservice.common.event.vehicle.VehicleDeletedEvent;
import org.mounanga.registrationservice.common.event.vehicle.VehicleUpdatedEvent;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("vehicleRegistrationId")
public class VehicleEventHandler {

    @EventHandler
    public void on(@NotNull VehicleCreatedEvent event, @NotNull VehicleRegistrationIdRepository repository){
        VehicleRegistrationId vehicleRegistrationId = new VehicleRegistrationId(event.getEventId(), event.getRegistrationId());
        repository.save(vehicleRegistrationId);
    }

    @EventHandler
    public void on(@NotNull VehicleUpdatedEvent event, @NotNull VehicleRegistrationIdRepository repository){

        VehicleRegistrationId vehicleRegistrationId = repository.findById(event.getEventId()).orElse(null);
        if(vehicleRegistrationId != null){
            vehicleRegistrationId.setRegistrationId(event.getRegistrationId());
            repository.save(vehicleRegistrationId);
        }
    }

    @EventHandler
    public void on(@NotNull VehicleDeletedEvent event, @NotNull VehicleRegistrationIdRepository  repository){
        repository.deleteById(event.getEventId());
    }
}

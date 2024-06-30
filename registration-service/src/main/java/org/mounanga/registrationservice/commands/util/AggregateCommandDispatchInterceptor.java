package org.mounanga.registrationservice.commands.util;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.jetbrains.annotations.NotNull;
import org.mounanga.registrationservice.commands.command.owner.CreateOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.DeleteOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.UpdateOwnerCommand;
import org.mounanga.registrationservice.commands.command.vehicle.CreateVehicleCommand;
import org.mounanga.registrationservice.commands.command.vehicle.DeleteVehicleCommand;
import org.mounanga.registrationservice.commands.command.vehicle.UpdateVehicleCommand;
import org.mounanga.registrationservice.commands.util.owner.OwnerCin;
import org.mounanga.registrationservice.commands.util.owner.OwnerCinRepository;
import org.mounanga.registrationservice.commands.util.vehicle.VehicleRegistration;
import org.mounanga.registrationservice.commands.util.vehicle.VehicleRegistrationRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Component
public class AggregateCommandDispatchInterceptor implements MessageDispatchInterceptor<CommandMessage<?>>{

    private final OwnerCinRepository ownerCinRepository;
    private final VehicleRegistrationRepository vehicleRegistrationRepository;

    public AggregateCommandDispatchInterceptor(OwnerCinRepository ownerCinRepository, VehicleRegistrationRepository vehicleRegistrationRepository) {
        this.ownerCinRepository = ownerCinRepository;
        this.vehicleRegistrationRepository = vehicleRegistrationRepository;
    }

    @NotNull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@NotNull List<? extends CommandMessage<?>> messages) {
        return (i, m) -> {
            if(CreateOwnerCommand.class.equals(m.getPayloadType())){
                validateCreateOwnerCommand(m);
            }else if (UpdateOwnerCommand.class.equals(m.getPayloadType())) {
                validateUpdateOwnerCommand(m);
            } else if (CreateVehicleCommand.class.equals(m.getPayloadType())) {
                validateCreateVehicleCommand(m);
            } else if (UpdateVehicleCommand.class.equals(m.getPayloadType())) {
                validateUpdateVehicleCommand(m);
            } else if (DeleteOwnerCommand.class.equals(m.getPayloadType())) {
                validateDeleteOwnerCommand(m);
            } else if (DeleteVehicleCommand.class.equals(m.getPayloadType())) {
                validateDeleteVehicleCommand(m);
            }
            return m;
        };
    }

    private void validateCreateOwnerCommand(@NotNull CommandMessage<?> m){
        final CreateOwnerCommand command = (CreateOwnerCommand) m.getPayload();
        if(ownerCinRepository.existsByCin(command.getCin())){
            throw new IllegalStateException(String.format("Owner with cin %s already exists", command.getCin()));
        }
    }

    private void validateUpdateOwnerCommand(@NotNull CommandMessage<?> m){
        final UpdateOwnerCommand command = (UpdateOwnerCommand) m.getPayload();
        final OwnerCin owner = ownerCinRepository.findById(command.getId()).orElse(null);
        if(owner == null){
            throw new IllegalStateException(String.format("Owner with id %s does not exist.", command.getId()));
        }
        final OwnerCin existingCin = ownerCinRepository.findByCin(command.getCin());
        if (existingCin != null && !existingCin.getOwnerId().equals(command.getId())) {
            throw new IllegalStateException(String.format("Owner with cin %s already exists", command.getCin()));
        }
    }

    private void validateDeleteOwnerCommand(@NotNull CommandMessage<?> m){
        final DeleteOwnerCommand command = (DeleteOwnerCommand) m.getPayload();
        if(!ownerCinRepository.existsById(command.getId())){
            throw new IllegalStateException(String.format("Owner with id %s does not exist", command.getId()));
        }
    }

    private void validateCreateVehicleCommand(@NotNull CommandMessage<?> m){
        final CreateVehicleCommand command = (CreateVehicleCommand) m.getPayload();
        if(vehicleRegistrationRepository.existsByRegistrationId(command.getRegistrationId())){
            throw new IllegalStateException(String.format("Vehicle with registrationId %s already exists", command.getRegistrationId()));
        }
        if(!ownerCinRepository.existsById(command.getOwnerId())){
            throw new IllegalStateException(String.format("Owner with id %s does not exist", command.getOwnerId()));
        }
    }

    private void validateUpdateVehicleCommand(@NotNull CommandMessage<?> m){
        final UpdateVehicleCommand command = (UpdateVehicleCommand) m.getPayload();
        final VehicleRegistration existingRegistration = vehicleRegistrationRepository.findByRegistrationId(command.getRegistrationId());
        if (existingRegistration != null && !existingRegistration.getVehicleId().equals(command.getId())) {
            throw new IllegalStateException(String.format("Vehicle with registrationId %s already exists", command.getRegistrationId()));
        }
    }

    private void validateDeleteVehicleCommand(@NotNull CommandMessage<?> m){
        final DeleteVehicleCommand command = (DeleteVehicleCommand) m.getPayload();
        if(vehicleRegistrationRepository.existsById(command.getId())){
            vehicleRegistrationRepository.deleteById(command.getId());
        }else{
            throw new IllegalStateException(String.format("Vehicle with id %s does not exist", command.getId()));
        }
    }
}
package org.mounanga.registrationservice.commands.util.validation;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.jetbrains.annotations.NotNull;
import org.mounanga.registrationservice.commands.command.owner.company.CreateCompanyOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.company.DeleteCompanyOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.company.UpdateCompanyOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.individual.CreateIndividualOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.individual.DeleteIndividualOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.individual.UpdateIndividualOwnerCommand;
import org.mounanga.registrationservice.commands.command.vehicle.CreateVehicleCommand;
import org.mounanga.registrationservice.commands.command.vehicle.DeleteVehicleCommand;
import org.mounanga.registrationservice.commands.command.vehicle.UpdateVehicleCommand;
import org.mounanga.registrationservice.commands.util.validation.owner.company.CompanyOwnerSiret;
import org.mounanga.registrationservice.commands.util.validation.owner.company.CompanyOwnerSiretRepository;
import org.mounanga.registrationservice.commands.util.validation.owner.individual.IndividualOwnerNip;
import org.mounanga.registrationservice.commands.util.validation.owner.individual.IndividualOwnerNipRepository;
import org.mounanga.registrationservice.commands.util.validation.vehicle.VehicleRegistrationId;
import org.mounanga.registrationservice.commands.util.validation.vehicle.VehicleRegistrationIdRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Component
public class AggregateCommandDispatchInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final VehicleRegistrationIdRepository vehicleRegistrationIdRepository;
    private final IndividualOwnerNipRepository individualOwnerNipRepository;
    private final CompanyOwnerSiretRepository companyOwnerSiretRepository;

    public AggregateCommandDispatchInterceptor(VehicleRegistrationIdRepository vehicleRegistrationIdRepository, IndividualOwnerNipRepository individualOwnerNipRepository, CompanyOwnerSiretRepository companyOwnerSiretRepository) {
        this.vehicleRegistrationIdRepository = vehicleRegistrationIdRepository;
        this.individualOwnerNipRepository = individualOwnerNipRepository;
        this.companyOwnerSiretRepository = companyOwnerSiretRepository;
    }


    @NotNull
    @Override
    public CommandMessage<?> handle(@NotNull CommandMessage<?> message) {
        return MessageDispatchInterceptor.super.handle(message);
    }

    @NotNull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@NotNull List<? extends CommandMessage<?>> messages) {
        return (i,m) -> {
            if(CreateVehicleCommand.class.equals(m.getPayloadType())){
                validateCreateVehicleCommand(m);
            } else if (UpdateVehicleCommand.class.equals(m.getPayloadType())) {
                validateUpdateVehicleCommand(m);
            } else if(DeleteVehicleCommand.class.equals(m.getPayloadType())){
                validateDeleteVehicleCommand(m);
            } else if (CreateIndividualOwnerCommand.class.equals(m.getPayloadType())) {
                validateCreateIndividualOwnerCommand(m);
            } else if (UpdateIndividualOwnerCommand.class.equals(m.getPayloadType())) {
                validateUpdateIndividualOwnerCommand(m);
            }else if (DeleteIndividualOwnerCommand.class.equals(m.getPayloadType())) {
                validateDeleteIndividualOwnerCommand(m);
            } else if (CreateCompanyOwnerCommand.class.equals(m.getPayloadType())) {
                validateCreateCompanyOwnerCommand(m);
            } else if (UpdateCompanyOwnerCommand.class.equals(m.getPayloadType())) {
                validateUpdateCompanyOwnerCommand(m);
            }else if (DeleteCompanyOwnerCommand.class.equals(m.getPayloadType())) {
                validateDeleteCompanyOwnerCommand(m);
            }
            return m;
        };
    }


    private void validateCreateVehicleCommand(@NotNull CommandMessage<?> m){
        final CreateVehicleCommand command = (CreateVehicleCommand) m.getPayload();
        if(vehicleRegistrationIdRepository.findByRegistrationId(command.getRegistrationId()) != null){
            throw new IllegalArgumentException(String.format("Vehicle with registration's id %s already exists", command.getRegistrationId()));
        }
    }

    private void validateUpdateVehicleCommand(@NotNull CommandMessage<?> m){
        final UpdateVehicleCommand command = (UpdateVehicleCommand) m.getPayload();
        VehicleRegistrationId vehicle = vehicleRegistrationIdRepository.findByRegistrationId(command.getRegistrationId());
        if(vehicle != null && !vehicle.getVehicleId().equals(command.getCommandId())){
            throw new IllegalArgumentException(String.format("Vehicle with id %s already exists", command.getCommandId()));
        }
    }

    private void validateDeleteVehicleCommand(@NotNull CommandMessage<?> m){
        final DeleteVehicleCommand command = (DeleteVehicleCommand) m.getPayload();
        if(!vehicleRegistrationIdRepository.existsById(command.getCommandId())){
            throw new IllegalArgumentException(String.format("Vehicle with id %s does not exist", command.getCommandId()));
        }
    }

    private void validateCreateIndividualOwnerCommand(@NotNull CommandMessage<?> m){
        final CreateIndividualOwnerCommand command = (CreateIndividualOwnerCommand) m.getPayload();
        if (individualOwnerNipRepository.findByNip(command.getNip()) != null){
            throw new IllegalArgumentException(String.format("Individual owner with nip %s already exists", command.getNip()));
        }
    }

    private void validateUpdateIndividualOwnerCommand(@NotNull CommandMessage<?> m){
        final UpdateIndividualOwnerCommand command = (UpdateIndividualOwnerCommand) m.getPayload();
        IndividualOwnerNip individualOwner = individualOwnerNipRepository.findByNip(command.getNip());
        if(individualOwner != null && !individualOwner.getId().equals(command.getCommandId())){
            throw new IllegalArgumentException(String.format("Individual owner with nip %s already exists", command.getCommandId()));
        }
    }

    private void validateDeleteIndividualOwnerCommand(@NotNull CommandMessage<?> m){
        final DeleteIndividualOwnerCommand command = (DeleteIndividualOwnerCommand) m.getPayload();
        if(!individualOwnerNipRepository.existsById(command.getCommandId())){
            throw new IllegalArgumentException(String.format("Individual owner with id %s does not exist", command.getCommandId()));
        }
    }

    private void validateCreateCompanyOwnerCommand(@NotNull CommandMessage<?> m){
        final CreateCompanyOwnerCommand command = (CreateCompanyOwnerCommand) m.getPayload();
        if (companyOwnerSiretRepository.findBySiret(command.getSiret()) != null){
            throw new IllegalArgumentException(String.format("Company owner with siret %s already exists", command.getSiret()));
        }
    }

    private void validateUpdateCompanyOwnerCommand(@NotNull CommandMessage<?> m){
        final UpdateCompanyOwnerCommand command = (UpdateCompanyOwnerCommand) m.getPayload();
        CompanyOwnerSiret companyOwner = companyOwnerSiretRepository.findBySiret(command.getSiret());
        if(companyOwner != null && !companyOwner.getOwnerId().equals(command.getCommandId())){
            throw new IllegalArgumentException(String.format("Company owner with siret %s already exists", command.getCommandId()));
        }
    }

    private void validateDeleteCompanyOwnerCommand(@NotNull CommandMessage<?> m){
        final DeleteCompanyOwnerCommand command = (DeleteCompanyOwnerCommand) m.getPayload();
        if(!companyOwnerSiretRepository.existsById(command.getCommandId())){
            throw new IllegalArgumentException(String.format("Company owner with id %s does not exist", command.getCommandId()));
        }
    }
}

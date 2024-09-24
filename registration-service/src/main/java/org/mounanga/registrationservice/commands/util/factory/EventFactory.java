package org.mounanga.registrationservice.commands.util.factory;

import org.jetbrains.annotations.Contract;
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
import org.mounanga.registrationservice.common.event.owner.company.CompanyOwnerCreatedEvent;
import org.mounanga.registrationservice.common.event.owner.company.CompanyOwnerDeletedEvent;
import org.mounanga.registrationservice.common.event.owner.company.CompanyOwnerUpdatedEvent;
import org.mounanga.registrationservice.common.event.owner.individual.IndividualOwnerCreatedEvent;
import org.mounanga.registrationservice.common.event.owner.individual.IndividualOwnerDeletedEvent;
import org.mounanga.registrationservice.common.event.owner.individual.IndividualOwnerUpdatedEvent;
import org.mounanga.registrationservice.common.event.vehicle.VehicleCreatedEvent;
import org.mounanga.registrationservice.common.event.vehicle.VehicleDeletedEvent;
import org.mounanga.registrationservice.common.event.vehicle.VehicleUpdatedEvent;

public class EventFactory {

    private EventFactory() {
        super();
    }

    @NotNull
    public static IndividualOwnerCreatedEvent create(@NotNull CreateIndividualOwnerCommand command){
        return new IndividualOwnerCreatedEvent(
                command.getCommandId(),
                command.getNip(),
                command.getFirstname(),
                command.getLastname(),
                command.getNationality(),
                command.getBirthdate(),
                command.getPlaceOfBirth(),
                command.getGender()
        );
    }

    @NotNull
    public static IndividualOwnerUpdatedEvent create(@NotNull UpdateIndividualOwnerCommand command){
        return new IndividualOwnerUpdatedEvent(
                command.getCommandId(),
                command.getNip(),
                command.getFirstname(),
                command.getLastname(),
                command.getNationality(),
                command.getBirthdate(),
                command.getPlaceOfBirth(),
                command.getGender()
        );
    }

    @NotNull
    public static IndividualOwnerDeletedEvent create(@NotNull DeleteIndividualOwnerCommand command){
        return new IndividualOwnerDeletedEvent(command.getCommandId());
    }

    @NotNull
    public static CompanyOwnerCreatedEvent create(@NotNull CreateCompanyOwnerCommand command){
        return new CompanyOwnerCreatedEvent(
                command.getCommandId(),
                command.getSiret(),
                command.getName(),
                command.getDescription(),
                command.getAddress()
        );
    }

    @NotNull
    public static CompanyOwnerUpdatedEvent create(@NotNull UpdateCompanyOwnerCommand command){
        return new CompanyOwnerUpdatedEvent(
                command.getCommandId(),
                command.getSiret(),
                command.getName(),
                command.getDescription(),
                command.getAddress()
        );
    }

    @NotNull
    public static CompanyOwnerDeletedEvent create(@NotNull DeleteCompanyOwnerCommand command){
        return new CompanyOwnerDeletedEvent(command.getCommandId());
    }

    @NotNull
    @Contract("_ -> new")
    public static VehicleCreatedEvent create(@NotNull CreateVehicleCommand command){
        return new VehicleCreatedEvent(
                command.getCommandId(),
                command.getRegistrationId(),
                command.getMarque(),
                command.getModel(),
                command.getTaxPower(),
                command.getDescription(),
                command.getOwnerId()
        );
    }

    @NotNull
    @Contract("_ -> new")
    public static VehicleUpdatedEvent create(@NotNull UpdateVehicleCommand command){
        return new VehicleUpdatedEvent(
                command.getCommandId(),
                command.getRegistrationId(),
                command.getMarque(),
                command.getModel(),
                command.getTaxPower(),
                command.getDescription(),
                command.getOwnerId()
        );
    }

    @NotNull
    @Contract("_ -> new")
    public static VehicleDeletedEvent create(@NotNull DeleteVehicleCommand command){
        return new VehicleDeletedEvent(command.getCommandId());
    }

}

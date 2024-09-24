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
import org.mounanga.registrationservice.commands.dto.CompanyOwnerRequestDTO;
import org.mounanga.registrationservice.commands.dto.IndividualOwnerRequestDTO;
import org.mounanga.registrationservice.commands.dto.VehicleRequestDTO;

import java.util.UUID;

public class CommandFactory {

    private CommandFactory() {
        super();
    }

    @NotNull
    @Contract("_ -> new")
    public static CreateIndividualOwnerCommand create(@NotNull IndividualOwnerRequestDTO dto){
        return new CreateIndividualOwnerCommand(
                UUID.randomUUID().toString(),
                dto.nip(),
                dto.firstname(),
                dto.lastname(),
                dto.nationality(),
                dto.birthdate(),
                dto.placeOfBirth(),
                dto.gender()
        );
    }

    @NotNull
    @Contract("_, _ -> new")
    public static UpdateIndividualOwnerCommand create(String id, @NotNull IndividualOwnerRequestDTO dto){
        return new UpdateIndividualOwnerCommand(
                id,
                dto.nip(),
                dto.firstname(),
                dto.lastname(),
                dto.nationality(),
                dto.birthdate(),
                dto.placeOfBirth(),
                dto.gender()
        );
    }

    @NotNull
    @Contract("_ -> new")
    public static DeleteIndividualOwnerCommand createDeleteIndividualOwnerCommand(String id){
        return new DeleteIndividualOwnerCommand(id);
    }

    @NotNull
    @Contract("_ -> new")
    public static DeleteCompanyOwnerCommand createDeleteCompanyOwnerCommand(String id){
        return new DeleteCompanyOwnerCommand(id);
    }

    @NotNull
    @Contract("_ -> new")
    public static CreateCompanyOwnerCommand create(@NotNull CompanyOwnerRequestDTO dto){
        return new CreateCompanyOwnerCommand(
                UUID.randomUUID().toString(),
                dto.siret(),
                dto.name(),
                dto.description(),
                dto.address()
        );
    }

    @NotNull
    @Contract("_, _ -> new")
    public static UpdateCompanyOwnerCommand create(String id, @NotNull CompanyOwnerRequestDTO dto){
        return new UpdateCompanyOwnerCommand(
                id,
                dto.siret(),
                dto.name(),
                dto.description(),
                dto.address()
        );
    }

    @NotNull
    @Contract("_ -> new")
    public static CreateVehicleCommand create(@NotNull VehicleRequestDTO dto){
        return new CreateVehicleCommand(
                UUID.randomUUID().toString(),
                dto.registrationId(),
                dto.marque(),
                dto.model(),
                dto.taxPower(),
                dto.description(),
                dto.ownerId()
        );
    }

    @NotNull
    public static UpdateVehicleCommand create(String id, @NotNull VehicleRequestDTO dto){
        return new UpdateVehicleCommand(
                id,
                dto.registrationId(),
                dto.marque(),
                dto.model(),
                dto.taxPower(),
                dto.description(),
                dto.ownerId()
        );
    }

    @NotNull
    @Contract("_ -> new")
    public static DeleteVehicleCommand createDeleteVehicleCommand(String id){
        return new DeleteVehicleCommand(id);
    }

}

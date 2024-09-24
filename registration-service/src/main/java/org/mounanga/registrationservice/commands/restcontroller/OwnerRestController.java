package org.mounanga.registrationservice.commands.restcontroller;

import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.mounanga.registrationservice.commands.command.owner.company.CreateCompanyOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.company.DeleteCompanyOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.company.UpdateCompanyOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.individual.CreateIndividualOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.individual.DeleteIndividualOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.individual.UpdateIndividualOwnerCommand;
import org.mounanga.registrationservice.commands.dto.CompanyOwnerRequestDTO;
import org.mounanga.registrationservice.commands.dto.IndividualOwnerRequestDTO;
import org.mounanga.registrationservice.commands.util.factory.CommandFactory;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/owners")
public class OwnerRestController {

    private final CommandGateway commandGateway;

    public OwnerRestController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/create/individual")
    public CompletableFuture<String> createIndividualOwner(@RequestBody @Valid IndividualOwnerRequestDTO dto){
        CreateIndividualOwnerCommand command = CommandFactory.create(dto);
        return commandGateway.send(command);
    }

    @PostMapping("/create/company")
    public CompletableFuture<String> createCompanyOwner(@RequestBody @Valid CompanyOwnerRequestDTO dto){
        CreateCompanyOwnerCommand command = CommandFactory.create(dto);
        return commandGateway.send(command);
    }

    @PutMapping("/update/individual/{id}")
    public CompletableFuture<String> updateIndividualOwner(@PathVariable String id, @RequestBody @Valid IndividualOwnerRequestDTO dto){
        UpdateIndividualOwnerCommand command = CommandFactory.create(id, dto);
        return commandGateway.send(command);
    }

    @PutMapping("/update/company/{id}")
    public CompletableFuture<String> updateCompanyOwner(@PathVariable String id, @RequestBody @Valid CompanyOwnerRequestDTO dto){
        UpdateCompanyOwnerCommand command = CommandFactory.create(id, dto);
        return commandGateway.send(command);
    }

    @DeleteMapping("/delete/individual/{id}")
    public CompletableFuture<String> deleteIndividualOwner(@PathVariable String id){
        DeleteIndividualOwnerCommand command = CommandFactory.createDeleteIndividualOwnerCommand(id);
        return commandGateway.send(command);
    }

    @DeleteMapping("/delete/company/{id}")
    public CompletableFuture<String> deleteCompanyOwner(@PathVariable String id){
        DeleteCompanyOwnerCommand command = CommandFactory.createDeleteCompanyOwnerCommand(id);
        return commandGateway.send(command);
    }

}

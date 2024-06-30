package org.mounanga.registrationservice.commands.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.mounanga.registrationservice.commands.command.owner.CreateOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.DeleteOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.UpdateOwnerCommand;
import org.mounanga.registrationservice.commands.dto.OwnerRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/owners")
public class OwnerCommandRestController {

    private final CommandGateway commandGateway;

    public OwnerCommandRestController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/create")
    public CompletableFuture<String> create(@RequestBody OwnerRequestDTO dto){
        return commandGateway.send(new CreateOwnerCommand(
                UUID.randomUUID().toString(),
                dto.getFirstname(),
                dto.getLastname(),
                dto.getDateOfBirth(),
                dto.getPlaceOfBirth(),
                dto.getGender(),
                dto.getNationality(),
                dto.getCin()
        ));
    }

    @PutMapping("/update/{id}")
    public CompletableFuture<String> update(@PathVariable String id, @RequestBody OwnerRequestDTO dto){
        return commandGateway.send(new UpdateOwnerCommand(
                id,
                dto.getFirstname(),
                dto.getLastname(),
                dto.getDateOfBirth(),
                dto.getPlaceOfBirth(),
                dto.getGender(),
                dto.getNationality(),
                dto.getCin()
        ));
    }

    @DeleteMapping("/delete/{id}")
    public CompletableFuture<String> delete(@PathVariable String id){
        return commandGateway.send(new DeleteOwnerCommand(id));
    }
}

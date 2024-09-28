package org.mounanga.infractionservice.commands.restcontroller;

import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.mounanga.infractionservice.commands.command.CreateInfractionCommand;
import org.mounanga.infractionservice.commands.command.DeleteInfractionCommand;
import org.mounanga.infractionservice.commands.command.UpdateInfractionCommand;
import org.mounanga.infractionservice.commands.dto.InfractionRequestDTO;
import org.mounanga.infractionservice.commands.util.CommandFactory;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/infractions")
public class InfractionRestController {

    private final CommandGateway commandGateway;

    public InfractionRestController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/create")
    public CompletableFuture<String> create(@RequestBody @Valid InfractionRequestDTO dto){
        CreateInfractionCommand command = CommandFactory.create(dto);
        return commandGateway.send(command);
    }

    @PutMapping("/update/{id}")
    public CompletableFuture<String> update(@PathVariable String id, @RequestBody @Valid InfractionRequestDTO dto){
        UpdateInfractionCommand command = CommandFactory.create(id,dto);
        return commandGateway.send(command);
    }

    @DeleteMapping("/delete/{id}")
    public CompletableFuture<String> delete(@PathVariable String id){
        DeleteInfractionCommand command = CommandFactory.create(id);
        return commandGateway.send(command);
    }
}

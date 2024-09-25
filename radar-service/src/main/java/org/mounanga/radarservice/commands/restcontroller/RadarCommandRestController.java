package org.mounanga.radarservice.commands.restcontroller;


import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.mounanga.radarservice.commands.command.CreateRadarCommand;
import org.mounanga.radarservice.commands.command.DeleteRadarCommand;
import org.mounanga.radarservice.commands.command.UpdateRadarCommand;
import org.mounanga.radarservice.commands.dto.RadarRequestDTO;
import org.mounanga.radarservice.commands.util.CommandFactory;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/radars")
public class RadarCommandRestController {


    private final CommandGateway commandGateway;

    public RadarCommandRestController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/create")
    public CompletableFuture<String> create(@RequestBody @Valid RadarRequestDTO dto){
        CreateRadarCommand command = CommandFactory.create(dto);
        return commandGateway.send(command);
    }

    @PutMapping("/update/{id}")
    public CompletableFuture<String> update(@PathVariable String id, @RequestBody @Valid RadarRequestDTO dto){
        UpdateRadarCommand command = CommandFactory.create(id,dto);
        return commandGateway.send(command);
    }

    @DeleteMapping("/delete/{id}")
    public CompletableFuture<String> delete(@PathVariable String id){
        DeleteRadarCommand command = CommandFactory.create(id);
        return commandGateway.send(command);
    }

}

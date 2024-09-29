package org.mounanga.radarcommandservice.restcontroller;


import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;

import org.mounanga.radarcommandservice.command.CreateRadarCommand;
import org.mounanga.radarcommandservice.command.DeleteRadarCommand;
import org.mounanga.radarcommandservice.command.UpdateRadarCommand;
import org.mounanga.radarcommandservice.dto.RadarRequestDTO;
import org.mounanga.radarcommandservice.util.factory.CommandFactory;
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

package org.mounanga.radarservice.commands.command;

import lombok.Getter;

@Getter
public class DeleteRadarCommand extends BaseCommand<String> {

    public DeleteRadarCommand(String id) {
        super(id);
    }
}

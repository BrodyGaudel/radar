package org.mounanga.registrationservice.commands.command.owner;

import org.mounanga.registrationservice.commands.command.BaseCommand;

public class DeleteOwnerCommand extends BaseCommand<String> {
    public DeleteOwnerCommand(String id) {
        super(id);
    }
}

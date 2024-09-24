package org.mounanga.registrationservice.commands.command.owner.company;

import lombok.Getter;
import org.mounanga.registrationservice.commands.command.BaseCommand;

@Getter
public class CreateCompanyOwnerCommand extends BaseCommand<String> {

    private final String siret;
    private final String name;
    private final String description;
    private final String address;

    public CreateCompanyOwnerCommand(String commandId, String siret, String name, String description, String address) {
        super(commandId);
        this.siret = siret;
        this.name = name;
        this.description = description;
        this.address = address;
    }
}

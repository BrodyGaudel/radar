package org.mounanga.registrationservice.commands.command.owner.individual;

import lombok.Getter;
import org.mounanga.registrationservice.commands.command.BaseCommand;
import org.mounanga.registrationservice.common.enums.Gender;

import java.time.LocalDate;

@Getter
public class UpdateIndividualOwnerCommand extends BaseCommand<String> {

    private final String nip;
    private final String firstname;
    private final String lastname;
    private final String nationality;
    private final LocalDate birthdate;
    private final String placeOfBirth;
    private final Gender gender;

    public UpdateIndividualOwnerCommand(String commandId, String nip, String firstname, String lastname, String nationality, LocalDate birthdate, String placeOfBirth, Gender gender) {
        super(commandId);
        this.nip = nip;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationality = nationality;
        this.birthdate = birthdate;
        this.placeOfBirth = placeOfBirth;
        this.gender = gender;
    }
}

package org.mounanga.registrationservice.commands.aggregate;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.jetbrains.annotations.NotNull;
import org.mounanga.registrationservice.commands.command.owner.CreateOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.DeleteOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.UpdateOwnerCommand;
import org.mounanga.registrationservice.common.enums.Gender;
import org.mounanga.registrationservice.common.event.owner.OwnerCreatedEvent;
import org.mounanga.registrationservice.common.event.owner.OwnerDeletedEvent;
import org.mounanga.registrationservice.common.event.owner.OwnerUpdatedEvent;

import java.time.LocalDate;

@Aggregate
@Slf4j
@Getter
public class OwnerAggregate {

    @AggregateIdentifier
    private String ownerId;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private Gender gender;
    private String nationality;
    private String cin;

    public OwnerAggregate() {
    }

    @CommandHandler
    public OwnerAggregate(@NotNull CreateOwnerCommand command) {
        log.info("CreateOwnerCommand handled");
        AggregateLifecycle.apply( new OwnerCreatedEvent(
                command.getId(),
                command.getFirstname(),
                command.getLastname(),
                command.getDateOfBirth(),
                command.getPlaceOfBirth(),
                command.getGender(),
                command.getNationality(),
                command.getCin()
        ));
    }

    @EventSourcingHandler
    public void on(@NotNull OwnerCreatedEvent event){
        log.info("OwnerCreatedEvent handled");
        this.ownerId = event.getId();
        this.firstname = event.getFirstname();
        this.lastname = event.getLastname();
        this.dateOfBirth = event.getDateOfBirth();
        this.placeOfBirth = event.getPlaceOfBirth();
        this.nationality = event.getNationality();
        this.gender = event.getGender();
        this.cin = event.getCin();
    }

    @CommandHandler
    public void handle(@NotNull UpdateOwnerCommand command){
        log.info("UpdateOwnerCommand handled");
        AggregateLifecycle.apply( new OwnerUpdatedEvent(
                command.getId(),
                command.getFirstname(),
                command.getLastname(),
                command.getDateOfBirth(),
                command.getPlaceOfBirth(),
                command.getGender(),
                command.getNationality(),
                command.getCin()
        ));
    }
    
    @EventSourcingHandler
    public void on(@NotNull OwnerUpdatedEvent event){
        log.info("OwnerUpdatedEvent handled");
        this.ownerId = event.getId();
        this.firstname = event.getFirstname();
        this.lastname = event.getLastname();
        this.dateOfBirth = event.getDateOfBirth();
        this.placeOfBirth = event.getPlaceOfBirth();
        this.gender = event.getGender();
        this.nationality = event.getNationality();
        this.cin = event.getCin();
    }

    @CommandHandler
    public void handle(@NotNull DeleteOwnerCommand command){
        log.info("DeleteOwnerCommand command");
        OwnerDeletedEvent event = new OwnerDeletedEvent(command.getId());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(@NotNull OwnerDeletedEvent event){
        log.info("OwnerDeletedEvent handled");
        this.ownerId = event.getId();
        this.firstname = null;
        this.lastname = null;
        this.dateOfBirth = null;
        this.placeOfBirth = null;
        this.gender = null;
        this.nationality = null;
        this.cin = null;
    }
}

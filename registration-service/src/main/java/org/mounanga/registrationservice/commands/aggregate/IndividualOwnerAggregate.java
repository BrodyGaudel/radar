package org.mounanga.registrationservice.commands.aggregate;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.jetbrains.annotations.NotNull;
import org.mounanga.registrationservice.commands.command.owner.individual.CreateIndividualOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.individual.DeleteIndividualOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.individual.UpdateIndividualOwnerCommand;
import org.mounanga.registrationservice.commands.util.factory.EventFactory;
import org.mounanga.registrationservice.common.enums.Gender;
import org.mounanga.registrationservice.common.event.owner.individual.IndividualOwnerCreatedEvent;
import org.mounanga.registrationservice.common.event.owner.individual.IndividualOwnerDeletedEvent;
import org.mounanga.registrationservice.common.event.owner.individual.IndividualOwnerUpdatedEvent;

import java.time.LocalDate;


@Aggregate
@Slf4j
@Getter
public class IndividualOwnerAggregate {

    @AggregateIdentifier
    private String ownerId;
    private String nip;
    private String firstname;
    private String lastname;
    private String nationality;
    private LocalDate birthdate;
    private String placeOfBirth;
    private Gender gender;

    public IndividualOwnerAggregate() {
        super();
    }

    @CommandHandler
    public IndividualOwnerAggregate(CreateIndividualOwnerCommand command){
        log.info("Handle CreateIndividualOwnerCommand");
        IndividualOwnerCreatedEvent event = EventFactory.create(command);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateIndividualOwnerCommand command){
        log.info("Handle UpdateIndividualOwnerCommand");
        IndividualOwnerUpdatedEvent event = EventFactory.create(command);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(DeleteIndividualOwnerCommand command){
        log.info("Handle DeleteIndividualOwnerCommand");
        IndividualOwnerDeletedEvent event = EventFactory.create(command);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(@NotNull IndividualOwnerCreatedEvent event){
        log.info("Handle IndividualOwnerCreatedEvent");
        this.ownerId = event.getEventId();
        setAggregateItems(event.getNip(), event.getFirstname(), event.getLastname(), event.getNationality(), event.getBirthdate(), event.getPlaceOfBirth(), event.getGender());
    }

    @EventSourcingHandler
    public void on(@NotNull IndividualOwnerUpdatedEvent event){
        log.info("Handle IndividualOwnerUpdatedEvent");
        this.ownerId = event.getEventId();
        setAggregateItems(event.getNip(), event.getFirstname(), event.getLastname(), event.getNationality(), event.getBirthdate(), event.getPlaceOfBirth(), event.getGender());
    }

    @EventSourcingHandler
    public void on(@NotNull IndividualOwnerDeletedEvent event){
        log.info("Handle IndividualOwnerDeletedEvent");
        this.ownerId = event.getEventId();
        setAggregateItems(null,null, null, null, null, null, null);
    }

    private void setAggregateItems(String nip, String firstname, String lastname, String nationality, LocalDate birthdate, String placeOfBirth, Gender gender) {
        this.nip = nip;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationality = nationality;
        this.birthdate = birthdate;
        this.placeOfBirth = placeOfBirth;
        this.gender = gender;
    }

}
package org.mounanga.registrationservice.commands.aggregate;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.jetbrains.annotations.NotNull;
import org.mounanga.registrationservice.commands.command.owner.company.CreateCompanyOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.company.DeleteCompanyOwnerCommand;
import org.mounanga.registrationservice.commands.command.owner.company.UpdateCompanyOwnerCommand;
import org.mounanga.registrationservice.commands.util.factory.EventFactory;
import org.mounanga.registrationservice.common.event.owner.company.CompanyOwnerCreatedEvent;
import org.mounanga.registrationservice.common.event.owner.company.CompanyOwnerDeletedEvent;
import org.mounanga.registrationservice.common.event.owner.company.CompanyOwnerUpdatedEvent;

@Getter
@Slf4j
@Aggregate
public class CompanyOwnerAggregate {

    @AggregateIdentifier
    private String ownerId;
    private String siret;
    private String name;
    private String description;
    private String address;

    public CompanyOwnerAggregate() {
        super();
    }

    @CommandHandler
    public CompanyOwnerAggregate(CreateCompanyOwnerCommand command) {
        log.info("Handle CreateCompanyOwnerCommand");
        CompanyOwnerCreatedEvent event = EventFactory.create(command);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateCompanyOwnerCommand command){
        log.info("Handle UpdateCompanyOwnerCommand");
        CompanyOwnerUpdatedEvent event = EventFactory.create(command);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(DeleteCompanyOwnerCommand command){
        log.info("Handle DeleteCompanyOwnerCommand");
        CompanyOwnerDeletedEvent event = EventFactory.create(command);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(@NotNull CompanyOwnerCreatedEvent event){
        log.info("Handle CompanyOwnerCreatedEvent");
        this.ownerId = event.getEventId();
        this.siret = event.getSiret();
        this.name = event.getName();
        this.description = event.getDescription();
        this.address = event.getAddress();
    }

    @EventSourcingHandler
    public void on(@NotNull CompanyOwnerUpdatedEvent event){
        log.info("Handle CompanyOwnerUpdatedEvent");
        this.ownerId = event.getEventId();
        this.siret = event.getSiret();
        this.name = event.getName();
        this.description = event.getDescription();
        this.address = event.getAddress();
    }

    @EventSourcingHandler
    public void on(@NotNull CompanyOwnerDeletedEvent event){
        log.info("Handle CompanyOwnerDeletedEvent");
        this.ownerId = event.getEventId();
        this.siret = null;
        this.name = null;
        this.description = null;
        this.address = null;
    }
}

package org.mounanga.registrationservice.commands.util.validation.owner.company;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.jetbrains.annotations.NotNull;
import org.mounanga.registrationservice.common.event.owner.company.CompanyOwnerCreatedEvent;
import org.mounanga.registrationservice.common.event.owner.company.CompanyOwnerDeletedEvent;
import org.mounanga.registrationservice.common.event.owner.company.CompanyOwnerUpdatedEvent;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("ownerCompanySiret")
public class CompanyOwnerEventHandler {

    @EventHandler
    public void on(@NotNull CompanyOwnerCreatedEvent event, @NotNull CompanyOwnerSiretRepository repository){
        CompanyOwnerSiret companyOwnerSiret = new CompanyOwnerSiret(event.getEventId(), event.getSiret());
        repository.save(companyOwnerSiret);
    }

    @EventHandler
    public void on(@NotNull CompanyOwnerUpdatedEvent event, @NotNull CompanyOwnerSiretRepository repository){
        CompanyOwnerSiret companyOwnerSiret = repository.findById(event.getEventId()).orElse(null);
        if(companyOwnerSiret != null){
            companyOwnerSiret.setSiret(event.getSiret());
            repository.save(companyOwnerSiret);
        }
    }

    @EventHandler
    public void on(@NotNull CompanyOwnerDeletedEvent event, @NotNull CompanyOwnerSiretRepository  repository){
        repository.deleteById(event.getEventId());
    }
}

package org.mounanga.registrationservice.queries.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.mounanga.registrationservice.common.event.owner.company.CompanyOwnerCreatedEvent;
import org.mounanga.registrationservice.common.event.owner.company.CompanyOwnerUpdatedEvent;
import org.mounanga.registrationservice.common.event.owner.individual.IndividualOwnerCreatedEvent;
import org.mounanga.registrationservice.common.event.owner.individual.IndividualOwnerUpdatedEvent;
import org.mounanga.registrationservice.common.event.vehicle.VehicleCreatedEvent;
import org.mounanga.registrationservice.common.event.vehicle.VehicleUpdatedEvent;
import org.mounanga.registrationservice.queries.entity.CompanyOwner;
import org.mounanga.registrationservice.queries.entity.IndividualOwner;
import org.mounanga.registrationservice.queries.entity.Owner;
import org.mounanga.registrationservice.queries.entity.Vehicle;

public class EntityFactory {

    private EntityFactory() {
        super();
    }

    @NotNull
    public static IndividualOwner create(@NotNull final IndividualOwnerCreatedEvent event){
        final IndividualOwner owner = new IndividualOwner();
        owner.setId(event.getEventId());
        owner.setFirstname(event.getFirstname());
        owner.setLastname(event.getLastname());
        owner.setGender(event.getGender());
        owner.setBirthdate(event.getBirthdate());
        owner.setPlaceOfBirth(event.getPlaceOfBirth());
        owner.setNationality(event.getNationality());
        owner.setNip(event.getNip());
        return owner;

    }

    @NotNull
    public static CompanyOwner create(@NotNull final CompanyOwnerCreatedEvent event){
        final CompanyOwner owner = new CompanyOwner();
        owner.setId(event.getEventId());
        owner.setSiret(event.getSiret());
        owner.setName(event.getName());
        owner.setDescription(event.getDescription());
        owner.setAddress(event.getAddress());
        return owner;
    }

    @NotNull
    @Contract("_, _ -> param2")
    public static IndividualOwner update(@NotNull final IndividualOwnerUpdatedEvent event, @NotNull final IndividualOwner owner){
        owner.setFirstname(event.getFirstname());
        owner.setGender(event.getGender());
        owner.setLastname(event.getLastname());
        owner.setBirthdate(event.getBirthdate());
        owner.setPlaceOfBirth(event.getPlaceOfBirth());
        owner.setNationality(event.getNationality());
        owner.setNip(event.getNip());
        return owner;
    }

    @Contract("_, _ -> param2")
    @NotNull
    public static CompanyOwner update(@NotNull final CompanyOwnerUpdatedEvent event, @NotNull CompanyOwner owner){
        owner.setSiret(event.getSiret());
        owner.setName(event.getName());
        owner.setDescription(event.getDescription());
        owner.setAddress(event.getAddress());
        return owner;
    }

    @NotNull
    public static Vehicle create(@NotNull final VehicleCreatedEvent event, @NotNull Owner owner){
        final Vehicle vehicle = new Vehicle();
        vehicle.setId(event.getEventId());
        vehicle.setMarque(event.getMarque());
        vehicle.setModel(event.getModel());
        vehicle.setDescription(event.getDescription());
        vehicle.setRegistrationId(event.getRegistrationId());
        vehicle.setTaxPower(event.getTaxPower());
        vehicle.setOwner(owner);
        return vehicle;
    }

    @NotNull
    @Contract("_, _, _ -> param3")
    public static Vehicle update(@NotNull final VehicleUpdatedEvent event, @NotNull Owner owner, @NotNull Vehicle vehicle){
        vehicle.setMarque(event.getMarque());
        vehicle.setRegistrationId(event.getRegistrationId());
        vehicle.setModel(event.getModel());
        vehicle.setDescription(event.getDescription());
        vehicle.setTaxPower(event.getTaxPower());
        vehicle.setOwner(owner);
        return vehicle;
    }

}

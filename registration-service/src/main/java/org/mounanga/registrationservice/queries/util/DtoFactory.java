package org.mounanga.registrationservice.queries.util;

import org.jetbrains.annotations.NotNull;
import org.mounanga.registrationservice.queries.dto.CompanyOwnerResponseDTO;
import org.mounanga.registrationservice.queries.dto.IndividualOwnerResponseDTO;
import org.mounanga.registrationservice.queries.dto.OwnerResponseDTO;
import org.mounanga.registrationservice.queries.dto.VehicleResponseDTO;
import org.mounanga.registrationservice.queries.entity.CompanyOwner;
import org.mounanga.registrationservice.queries.entity.IndividualOwner;
import org.mounanga.registrationservice.queries.entity.Owner;
import org.mounanga.registrationservice.queries.entity.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class DtoFactory {

    private DtoFactory() {
        super();
    }


    public static OwnerResponseDTO fromOwner(@NotNull Owner owner) {
        return switch (owner) {
            case CompanyOwner companyOwner -> fromCompanyOwner(companyOwner);
            case IndividualOwner individualOwner -> fromIndividualOwner(individualOwner);
            default -> throw new IllegalArgumentException("Unsupported owner type");
        };
    }

    public static List<OwnerResponseDTO> fromListOfOwners(@NotNull List<Owner> owners) {
        if(owners.isEmpty()){
            return new ArrayList<>();
        }
        return owners.stream().map(DtoFactory::fromOwner).toList();
    }

    public static VehicleResponseDTO fromVehicle(@NotNull Vehicle vehicle) {
        return VehicleResponseDTO.builder()
                .id(vehicle.getId())
                .model(vehicle.getModel())
                .marque(vehicle.getMarque())
                .description(vehicle.getDescription())
                .registrationId(vehicle.getRegistrationId())
                .ownerId(getOwnerId(vehicle.getOwner()))
                .taxPower(vehicle.getTaxPower())
                .createdBy(vehicle.getCreatedBy())
                .createdDate(vehicle.getCreatedDate())
                .lastModifiedBy(vehicle.getLastModifiedBy())
                .lastModifiedDate(vehicle.getLastModifiedDate())
                .build();
    }

    public static List<VehicleResponseDTO> fromListOfVehicle(@NotNull List<Vehicle> vehicles){
        if(vehicles.isEmpty()){
            return new ArrayList<>();
        }
        return vehicles.stream().map(DtoFactory::fromVehicle).toList();
    }


    @NotNull
    private static IndividualOwnerResponseDTO fromIndividualOwner(@NotNull IndividualOwner individualOwner) {
        final IndividualOwnerResponseDTO dto = new IndividualOwnerResponseDTO();
        dto.setId(individualOwner.getId());
        dto.setFirstname(individualOwner.getFirstname());
        dto.setLastname(individualOwner.getLastname());
        dto.setPlaceOfBirth(individualOwner.getPlaceOfBirth());
        dto.setBirthdate(individualOwner.getBirthdate());
        dto.setNationality(individualOwner.getNationality());
        dto.setNip(individualOwner.getNip());
        dto.setGender(individualOwner.getGender());
        dto.setCreatedBy(individualOwner.getCreatedBy());
        dto.setCreatedDate(individualOwner.getCreatedDate());
        dto.setLastModifiedBy(individualOwner.getLastModifiedBy());
        dto.setLastModifiedDate(individualOwner.getLastModifiedDate());
        return dto;
    }



    @NotNull
    private static CompanyOwnerResponseDTO fromCompanyOwner(@NotNull CompanyOwner companyOwner) {
        final CompanyOwnerResponseDTO dto = new CompanyOwnerResponseDTO();
        dto.setId(companyOwner.getId());
        dto.setName(companyOwner.getName());
        dto.setAddress(companyOwner.getAddress());
        dto.setDescription(companyOwner.getDescription());
        dto.setSiret(companyOwner.getSiret());
        dto.setCreatedBy(companyOwner.getCreatedBy());
        dto.setCreatedDate(companyOwner.getCreatedDate());
        dto.setLastModifiedBy(companyOwner.getLastModifiedBy());
        dto.setLastModifiedDate(companyOwner.getLastModifiedDate());
        return  dto;
    }

    private static String getOwnerId(Owner owner) {
        if (owner == null){
            return null;
        }
        return owner.getId();
    }
}

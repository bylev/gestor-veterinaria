package com.veterinaria.gestion_mascotas.persistence.mapper;


import com.veterinaria.gestion_mascotas.domain.model.Owner;
import com.veterinaria.gestion_mascotas.persistence.entity.Tutor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PetMapper.class})
public interface OwnerMapper {
    @Mappings({
            @Mapping(source="idTutor", target ="ownerId"),
            @Mapping(source = "mascotas", target = "mascotas"),
            @Mapping(target="mascotaIds", ignore = true)
    })

    Owner toOwner(Tutor tutor);

    @InheritInverseConfiguration
    @Mapping(source = "mascotas", target = "mascotas")
    Tutor toTutor(Owner  owner);

    List<Owner> toOwners(List<Tutor> tutors);
}

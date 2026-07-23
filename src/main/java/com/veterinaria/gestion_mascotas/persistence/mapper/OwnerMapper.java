package com.veterinaria.gestion_mascotas.persistence.mapper;


import com.veterinaria.gestion_mascotas.domain.service.Owner;
import com.veterinaria.gestion_mascotas.persistence.entity.Tutor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OwnerMapper {
    @Mappings({
            @Mapping(source="idTutor", target ="ownerId"),
            @Mapping(target="mascotaId", ignore = true)
    })

    Owner toOwner(Tutor tutor);

    @InheritInverseConfiguration
    @Mapping(target="mascotas", ignore = true)
    Tutor toTutor(Owner  owner);

    List<Tutor>  toOwners(List<Tutor> tutors);
}

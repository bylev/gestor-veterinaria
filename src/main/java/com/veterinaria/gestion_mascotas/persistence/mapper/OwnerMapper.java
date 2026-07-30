package com.veterinaria.gestion_mascotas.persistence.mapper;


import com.veterinaria.gestion_mascotas.domain.model.Owner;
import com.veterinaria.gestion_mascotas.persistence.entity.Mascota;
import com.veterinaria.gestion_mascotas.persistence.entity.Tutor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.ArrayList;

@Mapper(componentModel = "spring", uses = {PetMapper.class})
public interface OwnerMapper {
    @Mappings({
            @Mapping(source="idTutor", target ="ownerId"),
            @Mapping(source = "mascotas", target = "mascotas"),
            @Mapping(target="mascotaIds", expression = "java(getMascotaIds(tutor))")
    })

    Owner toOwner(Tutor tutor);

    @InheritInverseConfiguration
    @Mapping(source = "mascotas", target = "mascotas")
    Tutor toTutor(Owner  owner);

    List<Owner> toOwners(List<Tutor> tutors);

    default List<Integer> getMascotaIds(Tutor tutor) {
        List<Integer> mascotaIds = new ArrayList<>();

        if (tutor.getMascotas() == null || tutor.getMascotas().isEmpty()) {
            return mascotaIds;
        }

        for (Mascota mascota : tutor.getMascotas()) {
            mascotaIds.add(mascota.getIdMascota());
        }

        return mascotaIds;
    }
}

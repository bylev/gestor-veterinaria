package com.veterinaria.gestion_mascotas.persistence.mapper;

import com.veterinaria.gestion_mascotas.domain.model.Pet;
import com.veterinaria.gestion_mascotas.persistence.entity.Mascota;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetMapper {

    @Mappings({
            @Mapping(source = "idMascota", target = "mascotaId"),
            @Mapping(source = "tutor.idTutor", target = "ownerId")
    })
    Pet toPet(Mascota mascota);

    List<Pet> toPets(List<Mascota> mascotas);

    @InheritInverseConfiguration
    @Mapping(target = "tutor", ignore = true)
    Mascota toMascota(Pet pet);
}

package com.veterinaria.gestion_mascotas.persistence.mapper;

import com.veterinaria.gestion_mascotas.domain.service.Pet;
import com.veterinaria.gestion_mascotas.persistence.entity.Mascota;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetMapper {

    @Mappings({
            @Mapping(source = "idMascota", target = "mascotaId")
    })
    Pet toPet(Mascota mascota);

    List<Pet> toPets(List<Mascota> mascotas);

    @InheritInverseConfiguration
    Mascota toMascota(Pet pet);
}

package com.veterinaria.gestion_mascotas.persistence.mapper;

import com.veterinaria.gestion_mascotas.domain.model.Pet;
import com.veterinaria.gestion_mascotas.domain.model.PetOwner;
import com.veterinaria.gestion_mascotas.persistence.entity.Mascota;
import com.veterinaria.gestion_mascotas.persistence.entity.Tutor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetMapper {

    @Mappings({
            @Mapping(source = "idMascota", target = "mascotaId"),
            @Mapping(source = "tutor.idTutor", target = "ownerId"),
            @Mapping(source = "tutor", target = "owner"),
            @Mapping(source = "imageUrl", target = "imageUrl")
    })
    Pet toPet(Mascota mascota);

    List<Pet> toPets(List<Mascota> mascotas);


    // Datos del dueño que viajan dentro de cada mascota
    @Mapping(source = "idTutor", target = "ownerId")
    PetOwner toPetOwner(Tutor tutor);

    @InheritInverseConfiguration
    @Mapping(target = "tutor", ignore = true)
    Mascota toMascota(Pet pet);
}

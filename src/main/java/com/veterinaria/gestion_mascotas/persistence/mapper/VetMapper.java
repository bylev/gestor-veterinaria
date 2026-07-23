package com.veterinaria.gestion_mascotas.persistence.mapper;


import com.veterinaria.gestion_mascotas.domain.model.Vet;
import com.veterinaria.gestion_mascotas.persistence.entity.Veterinario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VetMapper {
    @Mappings({
            @Mapping(source="idVeterinario", target= "vetId"),
            @Mapping(target="citaId", ignore = true)
    })

    Vet toVet(Veterinario veterinario);

    List<Vet> toVets(List<Veterinario> veterinarios);

    @InheritInverseConfiguration
    Veterinario toVeterinario(Vet vet);
}

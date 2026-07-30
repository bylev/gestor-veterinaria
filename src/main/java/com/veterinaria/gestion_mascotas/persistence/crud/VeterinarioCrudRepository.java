package com.veterinaria.gestion_mascotas.persistence.crud;

import com.veterinaria.gestion_mascotas.persistence.entity.Veterinario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VeterinarioCrudRepository extends CrudRepository<Veterinario, Integer> {
    List<Veterinario> findByNumLicencia(String numLicencia);

    List<Veterinario> findByEspecialidad(String especialidad);

    Optional<Veterinario> findByEmail(String email);
}

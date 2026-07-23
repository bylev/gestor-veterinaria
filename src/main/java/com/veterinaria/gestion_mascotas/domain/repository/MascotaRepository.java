package com.veterinaria.gestion_mascotas.domain.repository;

import com.veterinaria.gestion_mascotas.domain.model.Pet;

import java.util.List;
import java.util.Optional;

public interface MascotaRepository {
    List<Pet> getAll();
    Optional<Pet> getById(Integer mascotaId);
    List<Pet> getByName(String nombre);
    List<Pet> getByEdad(int edad);
    Pet save(Pet pet);
    void delete(Integer mascotaId);
}
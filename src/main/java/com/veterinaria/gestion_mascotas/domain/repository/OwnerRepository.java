package com.veterinaria.gestion_mascotas.domain.repository;

import com.veterinaria.gestion_mascotas.domain.model.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository {
    List<Owner> getAll();
    Optional<Owner> getById(Integer ownerId);
    List<Owner> getByName(String nombre);
    List<Owner> getByLastName(String apellido);
    Optional<Owner> getByEmail(String email);
    List<Owner> getByMascotaId(Integer mascotaId);
    Owner save(Owner owner);
    void delete(Integer ownerId);
}

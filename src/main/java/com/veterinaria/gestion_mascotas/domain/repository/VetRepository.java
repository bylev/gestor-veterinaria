package com.veterinaria.gestion_mascotas.domain.repository;

import com.veterinaria.gestion_mascotas.domain.service.Vet;

import java.util.List;
import java.util.Optional;

public interface VetRepository {
    List<Vet> getAll();
    Optional<Vet> getById(Integer vetId);
    Optional<Vet> getByLicenseNumber(String numLicencia);
    List<Vet> getBySpecialty(String especialidad);
    Vet save(Vet vet);
    void delete(Integer vetId);
}

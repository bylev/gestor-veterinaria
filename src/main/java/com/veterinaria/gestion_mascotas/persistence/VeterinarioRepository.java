package com.veterinaria.gestion_mascotas.persistence;

import com.veterinaria.gestion_mascotas.domain.model.Vet;
import com.veterinaria.gestion_mascotas.domain.repository.VetRepository;
import com.veterinaria.gestion_mascotas.persistence.crud.VeterinarioCrudRepository;
import com.veterinaria.gestion_mascotas.persistence.entity.Veterinario;
import com.veterinaria.gestion_mascotas.persistence.mapper.VetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VeterinarioRepository implements VetRepository {
    @Autowired
    private VeterinarioCrudRepository veterinarioCrudRepository;

    @Autowired
    private VetMapper mapper;

    @Override
    public List<Vet> getAll() {
        List<Veterinario> veterinarios = new ArrayList<>();
        veterinarioCrudRepository.findAll().forEach(veterinarios::add);
        return mapper.toVets(veterinarios);
    }

    @Override
    public Optional<Vet> getById(Integer vetId) {
        return veterinarioCrudRepository.findById(vetId).map(mapper::toVet);
    }

    @Override
    public Optional<Vet> getByLicenseNumber(String numLicencia) {
        return veterinarioCrudRepository.findByNumLicencia(numLicencia).stream().findFirst().map(mapper::toVet);
    }

    @Override
    public List<Vet> getBySpecialty(String especialidad) {
        return mapper.toVets(veterinarioCrudRepository.findByEspecialidad(especialidad));
    }

    @Override
    public Vet save(Vet vet) {
        return mapper.toVet(veterinarioCrudRepository.save(mapper.toVeterinario(vet)));
    }

    @Override
    public void delete(Integer vetId) {
        veterinarioCrudRepository.deleteById(vetId);
    }
}

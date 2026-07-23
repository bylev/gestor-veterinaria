package com.veterinaria.gestion_mascotas.persistence;

import com.veterinaria.gestion_mascotas.domain.model.Owner;
import com.veterinaria.gestion_mascotas.domain.repository.OwnerRepository;
import com.veterinaria.gestion_mascotas.persistence.crud.TutorCrudRepository;
import com.veterinaria.gestion_mascotas.persistence.entity.Tutor;
import com.veterinaria.gestion_mascotas.persistence.mapper.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TutorRepository implements OwnerRepository {
    @Autowired
    private TutorCrudRepository tutorCrudRepository;

    @Autowired
    private OwnerMapper mapper;

    @Override
    public List<Owner> getAll() {
        List<Tutor> tutores = new ArrayList<>();
        tutorCrudRepository.findAll().forEach(tutores::add);
        return mapper.toOwners(tutores);
    }

    @Override
    public Optional<Owner> getById(Integer ownerId) {
        return tutorCrudRepository.findById(ownerId).map(mapper::toOwner);
    }

    @Override
    public List<Owner> getByName(String nombre) {
        return mapper.toOwners(tutorCrudRepository.findByNombre(nombre));
    }

    @Override
    public List<Owner> getByLastName(String apellido) {
        return mapper.toOwners(tutorCrudRepository.findByApellido(apellido));
    }

    @Override
    public Optional<Owner> getByEmail(String email) {
        return tutorCrudRepository.findByEmail(email).stream().findFirst().map(mapper::toOwner);
    }

    @Override
    public List<Owner> getByMascotaId(Integer mascotaId) {
        return mapper.toOwners(tutorCrudRepository.findByMascotasIdMascota(mascotaId));
    }

    @Override
    public Owner save(Owner owner) {
        return mapper.toOwner(tutorCrudRepository.save(mapper.toTutor(owner)));
    }

    @Override
    public void delete(Integer ownerId) {
        tutorCrudRepository.deleteById(ownerId);
    }
}

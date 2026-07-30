package com.veterinaria.gestion_mascotas.persistence;

import com.veterinaria.gestion_mascotas.domain.model.Owner;
import com.veterinaria.gestion_mascotas.domain.repository.OwnerRepository;
import com.veterinaria.gestion_mascotas.persistence.crud.TutorCrudRepository;
import com.veterinaria.gestion_mascotas.persistence.entity.Mascota;
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
        return toOwnersWithMascotaIds(tutores);
    }

    @Override
    public Optional<Owner> getById(Integer ownerId) {
        return tutorCrudRepository.findById(ownerId).map(this::toOwnerWithMascotaIds);
    }

    @Override
    public List<Owner> getByName(String nombre) {
        return toOwnersWithMascotaIds(tutorCrudRepository.findByNombre(nombre));
    }

    @Override
    public List<Owner> getByLastName(String apellido) {
        return toOwnersWithMascotaIds(tutorCrudRepository.findByApellido(apellido));
    }

    @Override
    public Optional<Owner> getByEmail(String email) {
        return tutorCrudRepository.findByEmail(email).stream().findFirst().map(this::toOwnerWithMascotaIds);
    }

    @Override
    public List<Owner> getByMascotaId(Integer mascotaId) {
        return toOwnersWithMascotaIds(tutorCrudRepository.findByMascotasIdMascota(mascotaId));
    }

    @Override
    public Owner save(Owner owner) {
        return toOwnerWithMascotaIds(tutorCrudRepository.save(mapper.toTutor(owner)));
    }

    @Override
    public void delete(Integer ownerId) {
        tutorCrudRepository.deleteById(ownerId);
    }

    private List<Owner> toOwnersWithMascotaIds(List<Tutor> tutores) {
        List<Owner> owners = new ArrayList<>();

        for (Tutor tutor : tutores) {
            owners.add(toOwnerWithMascotaIds(tutor));
        }

        return owners;
    }

    private Owner toOwnerWithMascotaIds(Tutor tutor) {
        Owner owner = mapper.toOwner(tutor);

        List<Integer> mascotaIds = new ArrayList<>();

        if (tutor.getMascotas() != null) {
            for (Mascota mascota : tutor.getMascotas()) {
                mascotaIds.add(mascota.getIdMascota());
            }
        }

        owner.setMascotaIds(mascotaIds);

        return owner;
    }
}

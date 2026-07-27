package com.veterinaria.gestion_mascotas.persistence;

import com.veterinaria.gestion_mascotas.domain.model.Pet;
import com.veterinaria.gestion_mascotas.domain.repository.PetRepository;
import com.veterinaria.gestion_mascotas.persistence.crud.MascotaCrudRepository;
import com.veterinaria.gestion_mascotas.persistence.entity.Mascota;
import com.veterinaria.gestion_mascotas.persistence.entity.Tutor;
import com.veterinaria.gestion_mascotas.persistence.mapper.PetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MascotaRepository implements PetRepository {
    @Autowired
    private MascotaCrudRepository mascotaCrudRepository;

    @Autowired
    private PetMapper mapper;

    @Override
    public List<Pet> getAll() {
        List<Mascota> mascotas = new ArrayList<>();
        mascotaCrudRepository.findAll().forEach(mascotas::add);
        return mapper.toPets(mascotas);
    }

    @Override
    public Optional<Pet> getById(Integer mascotaId) {
        return mascotaCrudRepository.findById(mascotaId).map(mapper::toPet);
    }

    @Override
    public List<Pet> getByName(String nombre) {
        return mapper.toPets(mascotaCrudRepository.findByNombreContainingIgnoreCase(nombre));
    }

    @Override
    public List<Pet> getByEdad(int edad) {
        return mapper.toPets(mascotaCrudRepository.findByEdad(edad));
    }

    @Override
    public Pet save(Pet pet) {
        Mascota mascota = mapper.toMascota(pet);

        if (pet.getOwnerId() != null) {
            Tutor tutor = new Tutor();
            tutor.setIdTutor(pet.getOwnerId());
            mascota.setTutor(tutor);
        }

        return mapper.toPet(mascotaCrudRepository.save(mascota));
    }

    @Override
    public void delete(Integer mascotaId) {
        mascotaCrudRepository.deleteById(mascotaId);
    }
}

package com.veterinaria.gestion_mascotas.domain.service;


import com.veterinaria.gestion_mascotas.domain.model.Pet;
import com.veterinaria.gestion_mascotas.domain.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;
    public List<Pet> getAll(){
        return  petRepository.getAll();
    }

    public Optional<Pet> getById(Integer mascotaId){
        return petRepository.getById(mascotaId);
    }

    public List<Pet> getByName(String name){
        return petRepository.getByName(name);
    }

    public List<Pet> getByEdad(int edad){
        return petRepository.getByEdad(edad);
    }

    public Pet save(Pet pet){
        return petRepository.save(pet);
    }

    public boolean delete(Integer mascotaId){
        if(getById(mascotaId).isPresent()){
            petRepository.delete(mascotaId);
            return true;
        }
        return false;
    }
}

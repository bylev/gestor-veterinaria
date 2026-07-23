package com.veterinaria.gestion_mascotas.domain.service;


import com.veterinaria.gestion_mascotas.domain.model.Vet;
import com.veterinaria.gestion_mascotas.domain.repository.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VetService {
    @Autowired
    private VetRepository vetRepository;


    public List<Vet> getAll(){
        return vetRepository.getAll();
    }

    public Optional<Vet> getById(Integer vetId){
        return vetRepository.getById(vetId);
    }

    public Optional<Vet> getByLicenseNumber(String numLicencia){
        return vetRepository.getByLicenseNumber(numLicencia);
    }

    public List<Vet> getBySpecialty(String specialty){
        return vetRepository.getBySpecialty(specialty);
    }

    public Vet save(Vet vet){
        return vetRepository.save(vet);
    }

    public boolean delete(Integer vetId){
        if(vetRepository.getById(vetId).isPresent()){
            vetRepository.delete(vetId);
            return true;
        }
        return false;
    }

}

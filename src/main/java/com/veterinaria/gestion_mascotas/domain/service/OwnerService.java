package com.veterinaria.gestion_mascotas.domain.service;

import com.veterinaria.gestion_mascotas.domain.model.Owner;
import com.veterinaria.gestion_mascotas.domain.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    @Autowired
    OwnerRepository ownerRepository;

    public List<Owner> getAll(){
        return ownerRepository.getAll();
    }

    public Optional<Owner> getById(Integer mascotaId) {
        return ownerRepository.getById(mascotaId);
    }

    public List<Owner> getByName(String name){
        return ownerRepository.getByName(name);
    }

    public List<Owner> getByLastName(String lastName){
        return ownerRepository.getByLastName(lastName);
    }

    public Optional<Owner> getByEmail(String email){
        return ownerRepository.getByEmail(email);
    }

    public List<Owner> getByMascotaId(Integer mascotaId){
        return ownerRepository.getByMascotaId(mascotaId);
    }

    public Owner save(Owner owner){
        return ownerRepository.save(owner);
    }

    public boolean delete(Integer ownerId){
        if(getById(ownerId).isPresent()){
            ownerRepository.delete(ownerId);
            return true;
        }
        return false;
    }


}

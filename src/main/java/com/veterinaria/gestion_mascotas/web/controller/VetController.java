package com.veterinaria.gestion_mascotas.web.controller;


import com.veterinaria.gestion_mascotas.domain.model.Vet;
import com.veterinaria.gestion_mascotas.domain.repository.VetRepository;
import com.veterinaria.gestion_mascotas.domain.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vet")
public class VetController {

    @Autowired
    private VetService vetService;
    @Autowired
    private VetRepository vetRepository;

    @GetMapping("")
    public List<Vet> getAll(){return vetService.getAll();}

    @GetMapping("/{id}")
    public Optional<Vet> getById(@PathVariable("id") Integer vetId){
        return  vetService.getById(vetId);
    }
    @GetMapping("/vet/{numLicense}")
    public Optional<Vet> getByLicenseNumber(@PathVariable String numLicense){
        return vetRepository.getByLicenseNumber(numLicense);
    }

    @GetMapping("/vet/{specialty}")
    public List<Vet> getBySpecialty(@PathVariable String specialty){
        return  vetRepository.getBySpecialty(specialty);
    }

    @PostMapping
    public Vet save(@RequestBody Vet vet){
        return vetService.save(vet);
    }

    @PostMapping("/{id}")
    public Vet delete(@PathVariable("id") Integer vetId){
        vetService.delete(vetId);
        return null;
    }
}

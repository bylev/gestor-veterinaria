package com.veterinaria.gestion_mascotas.web.controller;


import com.veterinaria.gestion_mascotas.domain.model.Vet;
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

    @GetMapping("")
    public List<Vet> getAll(){return vetService.getAll();}

    @GetMapping("/{id}")
    public Optional<Vet> getById(@PathVariable("id") Integer vetId){
        return  vetService.getById(vetId);
    }
    @GetMapping("/{numLicense}")
    public Optional<Vet> getByLicenseNumber(@PathVariable String numLicense){
        return vetService.getByLicenseNumber(numLicense);
    }

    @GetMapping("/{specialty}")
    public List<Vet> getBySpecialty(@PathVariable String specialty){
        return  vetService.getBySpecialty(specialty);
    }

    @PostMapping
    public Vet save(@RequestBody Vet vet){
        return vetService.save(vet);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Integer vetId){
        return vetService.delete(vetId);
    }
}

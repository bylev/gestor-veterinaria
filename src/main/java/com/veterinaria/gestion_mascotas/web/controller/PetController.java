package com.veterinaria.gestion_mascotas.web.controller;

import com.veterinaria.gestion_mascotas.domain.model.Pet;
import com.veterinaria.gestion_mascotas.domain.repository.PetRepository;
import com.veterinaria.gestion_mascotas.domain.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pet")

public class PetController {

    @Autowired
    private PetService petService;
    @Autowired
    private PetRepository petRepository;

    @GetMapping("")
    public List<Pet> getAll() {
        return petService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Pet> getById(@PathVariable Integer id) {
        return petService.getById(id);
    }

    @GetMapping("/pet/{name}")
    public List<Pet> getByName(@PathVariable String name) {
        return petService.getByName(name);
    }

    @GetMapping("/pet/{edad}")
    public List<Pet> getByEdad(@PathVariable Integer edad) {
        return petService.getByEdad(edad);
    }

    @PostMapping
    public Pet save(@RequestBody Pet pet) {
        return petService.save(pet);
    }

    @PostMapping("/{id}")
    public Pet delete(@PathVariable("id") Integer mascotaId) {
        petService.delete(mascotaId);
        return null;
    }

}

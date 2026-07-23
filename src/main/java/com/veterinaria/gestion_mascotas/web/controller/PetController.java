package com.veterinaria.gestion_mascotas.web.controller;

import com.veterinaria.gestion_mascotas.domain.model.Pet;
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

    @GetMapping("")
    public List<Pet> getAll() {
        return petService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Pet> getById(@PathVariable Integer id) {
        return petService.getById(id);
    }

    @GetMapping("/name/{name}")
    public List<Pet> getByName(@PathVariable String name) {
        return petService.getByName(name);
    }

    @GetMapping("/age/{edad}")
    public List<Pet> getByEdad(@PathVariable Integer edad) {
        return petService.getByEdad(edad);
    }

    @PostMapping
    public Pet save(@RequestBody Pet pet) {
        return petService.save(pet);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Integer mascotaId) {
        return petService.delete(mascotaId);
    }

}

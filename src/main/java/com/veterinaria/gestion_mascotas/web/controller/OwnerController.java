package com.veterinaria.gestion_mascotas.web.controller;

import com.veterinaria.gestion_mascotas.domain.model.Owner;
import com.veterinaria.gestion_mascotas.domain.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @GetMapping("")
    public List<Owner> getAll(){
        return  ownerService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Owner> getOwnerById(@PathVariable Integer id){
        return ownerService.getById(id);
    }

    @GetMapping("/pet/{mascotaId}")
    public List<Owner> getByMascotaId(@PathVariable Integer mascotaId){
        return ownerService.getByMascotaId(mascotaId);
    }

    @GetMapping("/name/{name}")
    public List<Owner> getByName(@PathVariable String name){
        return ownerService.getByName(name);
    }

    @GetMapping("/lastname/{lastName}")
    public List<Owner> getByLastName(@PathVariable String lastName){
        return ownerService.getByLastName(lastName);
    }

    @GetMapping("/email/{email}")
    public Optional<Owner> getByEmail(@PathVariable String email){
        return ownerService.getByEmail(email);
    }

    @PostMapping
    public Owner save(@RequestBody Owner owner){
        return ownerService.save(owner);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id){
        return ownerService.delete(id);
    }
}

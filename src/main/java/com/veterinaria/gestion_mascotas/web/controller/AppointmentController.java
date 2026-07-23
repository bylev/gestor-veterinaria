package com.veterinaria.gestion_mascotas.web.controller;


import com.veterinaria.gestion_mascotas.domain.model.Appointment;
import com.veterinaria.gestion_mascotas.domain.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("")
    public List<Appointment> getAll(){
        return appointmentService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Appointment> getCitaById(@PathVariable("id") Integer citaId){
        return appointmentService.getCitaById(citaId);
    }

    @GetMapping("/{mascotaId}")
    public List<Appointment> getByMascotaId(@PathVariable Integer mascotaId){
        return appointmentService.getByMascotaId(mascotaId);
    }

    @PostMapping
    public Appointment save(@RequestBody Appointment appointment){
        return appointmentService.save(appointment);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") Integer citaId){
        return appointmentService.delete(citaId);
    }
}

package com.veterinaria.gestion_mascotas.web.controller;


import com.veterinaria.gestion_mascotas.domain.model.Appointment;
import com.veterinaria.gestion_mascotas.domain.repository.AppointmentRepository;
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
    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("")
    public List<Appointment> getAll(){
        return appointmentService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Appointment> getCitaById(@PathVariable("id") Integer citaId){
        return appointmentService.getCitaById(citaId);
    }

    @GetMapping("/Appointment/{mascota_id}")
    public List<Appointment> getById(@PathVariable("mascota_id") Integer mascotaId){
        return appointmentService.getById(mascotaId);
    }

    @PostMapping
    public Appointment save(@RequestBody Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    @PostMapping("/{id}")
    public Appointment delete(@PathVariable("id") Integer citaId){
        appointmentService.delete(citaId);
        return null;
    }
}

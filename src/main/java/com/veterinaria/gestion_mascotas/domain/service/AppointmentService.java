package com.veterinaria.gestion_mascotas.domain.service;

import com.veterinaria.gestion_mascotas.domain.model.Appointment;
import com.veterinaria.gestion_mascotas.domain.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAll(){
        return appointmentRepository.getAll();
    }

    public List<Appointment> getById(Integer mascotaId){
        return appointmentRepository.getByMascotaId(mascotaId);
    }

    public Optional<Appointment> getCitaById(Integer citaId){
        return appointmentRepository.getCitaById(citaId);
    }

    public Appointment save(Appointment appointment){
        return  appointmentRepository.save(appointment);
    }

    public boolean delete(Integer citaId){
        //Verificar que exista
        if (getCitaById(citaId).isPresent()){
            appointmentRepository.delete(citaId);
            return true;
        }
        return false;
    }
}

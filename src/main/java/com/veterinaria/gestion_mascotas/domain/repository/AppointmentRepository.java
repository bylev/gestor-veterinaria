package com.veterinaria.gestion_mascotas.domain.repository;

import com.veterinaria.gestion_mascotas.domain.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {
    List<Appointment> getAll();
    Optional<Appointment> getCitaById(Integer citaId);
    List<Appointment> getByMascotaId(Integer mascotaId);
    Appointment save(Appointment appointment);
    void delete(Integer citaId);
}

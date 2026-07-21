package com.veterinaria.gestion_mascotas.domain.repository;

import com.veterinaria.gestion_mascotas.domain.service.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {
    List<Appointment> getAll();
    Optional<Appointment> getById(Integer citaId);
    List<Appointment> getByMascotaId(Integer mascotaId);
    Appointment save(Appointment appointment);
    void delete(Integer citaId);
}

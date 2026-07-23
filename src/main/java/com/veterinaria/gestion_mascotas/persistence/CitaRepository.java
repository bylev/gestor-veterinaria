package com.veterinaria.gestion_mascotas.persistence;

import com.veterinaria.gestion_mascotas.domain.model.Appointment;
import com.veterinaria.gestion_mascotas.domain.repository.AppointmentRepository;
import com.veterinaria.gestion_mascotas.persistence.crud.CitaCrudRepository;
import com.veterinaria.gestion_mascotas.persistence.entity.Cita;
import com.veterinaria.gestion_mascotas.persistence.entity.Mascota;
import com.veterinaria.gestion_mascotas.persistence.entity.Veterinario;
import com.veterinaria.gestion_mascotas.persistence.mapper.AppointmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CitaRepository implements AppointmentRepository {
    @Autowired
    private CitaCrudRepository citaCrudRepository;

    @Autowired
    private AppointmentMapper mapper;

    @Override
    public List<Appointment> getAll() {
        List<Cita> citas = new ArrayList<>();
        citaCrudRepository.findAll().forEach(citas::add);
        return mapper.toAppointmentList(citas);
    }

    @Override
    public Optional<Appointment> getCitaById(Integer citaId) {
        return citaCrudRepository.findById(citaId).map(mapper::toAppointment);
    }

    @Override
    public List<Appointment> getByMascotaId(Integer mascotaId) {
        return mapper.toAppointmentList(citaCrudRepository.findByMascotaIdMascota(mascotaId));
    }

    @Override
    public Appointment save(Appointment appointment) {
        Cita cita = mapper.toCita(appointment);

        if (appointment.getMascotaId() != null) {
            Mascota mascota = new Mascota();
            mascota.setIdMascota(appointment.getMascotaId());
            cita.setMascota(mascota);
        }

        if (appointment.getVeterinarioId() != null) {
            Veterinario veterinario = new Veterinario();
            veterinario.setIdVeterinario(appointment.getVeterinarioId());
            cita.setVeterinario(veterinario);
        }

        return mapper.toAppointment(citaCrudRepository.save(cita));
    }

    @Override
    public void delete(Integer citaId) {
        citaCrudRepository.deleteById(citaId);
    }
}

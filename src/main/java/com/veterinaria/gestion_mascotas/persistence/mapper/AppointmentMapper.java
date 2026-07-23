package com.veterinaria.gestion_mascotas.persistence.mapper;

import com.veterinaria.gestion_mascotas.domain.model.Appointment;
import com.veterinaria.gestion_mascotas.persistence.entity.Cita;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    @Mappings({
            @Mapping(source = "idCita", target = "citaId"),
            @Mapping(source = "mascota.idMascota", target = "mascotaId"),
            @Mapping(source = "veterinario.idVeterinario", target = "veterinarioId")
    })

    Appointment toAppointment(Cita cita);

    @InheritInverseConfiguration
    @Mapping(target = "mascota", ignore = true)
    @Mapping(target = "veterinario", ignore = true)

    Cita toCita(Appointment appointment);

    List<Appointment> toAppointmentList(List<Cita> citas);
}

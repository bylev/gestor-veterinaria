package com.veterinaria.gestion_mascotas.persistence.crud;

import com.veterinaria.gestion_mascotas.persistence.entity.Cita;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaCrudRepository extends CrudRepository<Cita, Integer> {
    List<Cita> findByMotivo(String motivo);

    List<Cita> findByVeterinarioIdVeterinario(Integer idVeterinario);

    List<Cita> findByEstado(String estado);

    List<Cita> findByMascotaIdMascota(Integer idMascota);
    List<Cita> findByFechaBetween(LocalDateTime  inicio, LocalDateTime fin);
    List<Cita> findByFechaAfter(LocalDateTime fecha);
    List<Cita> findByObservacionesContains(String observaciones);
}

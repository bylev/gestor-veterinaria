package com.veterinaria.gestion_mascotas.persistence.crud;

import com.veterinaria.gestion_mascotas.persistence.entity.Cita;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CitaCrudRepository extends CrudRepository<Cita, Integer> {
    List<Cita> findByMascotaIdMascota(Integer idMascota);
    List<Cita> findByEstado(String Estado);
}

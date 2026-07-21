package com.veterinaria.gestion_mascotas.persistence.crud;

import com.veterinaria.gestion_mascotas.persistence.entity.Tutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TutorCrudRepository extends CrudRepository<Tutor, Integer> {
    List<Tutor> findByApellido(String apellido);
    List<Tutor> findByNombre(String nombre);
    List<Tutor> findByEmail(String email);
    List<Tutor> findByMascotasIdMascota(Integer idMascota);
    List<Tutor> findByMascotasNombreContainingIgnoreCase(String nombreMascota);
}

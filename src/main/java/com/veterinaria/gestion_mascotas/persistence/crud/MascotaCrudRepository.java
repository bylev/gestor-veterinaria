package com.veterinaria.gestion_mascotas.persistence.crud;

import com.veterinaria.gestion_mascotas.persistence.entity.Mascota;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MascotaCrudRepository extends CrudRepository<Mascota, Integer>
{
    List<Mascota> findByNombreContainingIgnoreCase(String nombre);
    List<Mascota> findByEdad(Integer edad);
}

package com.veterinaria.gestion_mascotas.persistence.crud;

import com.veterinaria.gestion_mascotas.persistence.entity.Mascota;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MascotaCrudRepository extends CrudRepository<Mascota, Integer>
{
    List<Mascota> findByEspecie(String especie);
    List<Mascota> findByEspecieAndSexo(String especie, String sexo);
    List<Mascota> findByEdadGreaterThanOrderByEdadDesc(Integer edad);
    List<Mascota> findByPesoBetween(Float min, Float max);
    List<Mascota> findByNombreContainingIgnoreCase(String nombre);
    List<Mascota> findByEdad(Integer edad);
    List<Mascota> findByEdadLessThan(Integer edad);
}

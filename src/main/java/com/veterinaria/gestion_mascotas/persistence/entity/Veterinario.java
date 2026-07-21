package com.veterinaria.gestion_mascotas.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name="veterinarios")
public class Veterinario {
    @Id
    @Column(name="id_veterinario")
    private String idVeterinario;

    private String nombre;
    private String apellido;
    @Column(name="Num_Licencia")
    private String numLicencia;
    private String especialidad;


    public String getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(String idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumLicencia() {
        return numLicencia;
    }

    public void setNumLicencia(String numLicencia) {
        this.numLicencia = numLicencia;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

}

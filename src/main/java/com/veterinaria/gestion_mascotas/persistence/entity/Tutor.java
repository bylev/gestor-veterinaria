package com.veterinaria.gestion_mascotas.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="tutores")
public class Tutor {
    @Id
    @Column(name="id_tutor")
    private String idTutor;

    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
    private String telefono;

    //RELACIONES

    @OneToMany
    @JoinColumn(name="id_mascota")
    private List<Mascota> mascotas;


    // GETTERS AND SETTERS

    public String getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(String idTutor) {
        this.idTutor = idTutor;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}

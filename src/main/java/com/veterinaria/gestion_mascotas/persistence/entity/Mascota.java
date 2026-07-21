package com.veterinaria.gestion_mascotas.persistence.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="mascotas")

public class Mascota {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_mascota")
    private Integer idMascota;

    private String nombre;
    private String raza;
    private String especie;
    private String sexo;
    private float peso;
    private int edad;

    // Getter and Setter
    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer id) {
        this.idMascota = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}

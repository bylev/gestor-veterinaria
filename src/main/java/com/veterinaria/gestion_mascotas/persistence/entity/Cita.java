package com.veterinaria.gestion_mascotas.persistence.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.ManyToAny;

import java.time.LocalDateTime;

@Entity
@Table(name= "citas")
public class Cita {
    @Id
    @Column(name="id_cita")
    private String idCita;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    private String motivo;
    private String estado;

    // RELACIONES

    @ManyToOne
    @JoinColumn(name="id_mascota")
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name="id_veterinario")
    private Veterinario veterinario;

    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }
}

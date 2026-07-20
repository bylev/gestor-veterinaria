package com.veterinaria.gestion_mascotas.persistence.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.ManyToAny;

import java.time.LocalDateTime;

@Entity
@Table(name= "citas")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cita")
    private String idCita;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    private String motivo;
    private LocalDateTime fecha_registro;
    private String estado;

    // RELACIONES

    @ManyToOne
    @JoinColumn(name="id_mascota")
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name="id_veterinario")
    private Veterinario veterinario;





}

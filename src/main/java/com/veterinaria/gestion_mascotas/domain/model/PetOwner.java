package com.veterinaria.gestion_mascotas.domain.model;

/**
 * Owner summary nested inside a Pet response.
 * It has no list of pets, so the JSON does not loop Pet -> Owner -> Pet.
 */
public class PetOwner {
    private Integer ownerId;
    private String nombre;
    private String apellido;
    private String telefono;

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

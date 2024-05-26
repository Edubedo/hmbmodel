package com.hmbmodel.proyectohmbmodel;

public class Gimnasio {
    private String id;
    private String nombre;
    private String propietario;
    private String email;
    private String telefono;
    private String descripcion;
    private String propietarioEmail;

    public Gimnasio() {
        // Constructor vacío requerido para Firestore
    }

    public Gimnasio(String id, String nombre, String propietario, String email, String telefono, String descripcion, String propietarioEmail) {
        this.id = id;
        this.nombre = nombre;
        this.propietario = propietario;
        this.email = email;
        this.telefono = telefono;
        this.descripcion = descripcion;
        this.propietarioEmail = propietarioEmail;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPropietario() {
        return propietario;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPropietarioEmail() {
        return propietarioEmail;
    }
}

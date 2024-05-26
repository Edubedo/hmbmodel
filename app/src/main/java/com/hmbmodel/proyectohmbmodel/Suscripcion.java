package com.hmbmodel.proyectohmbmodel;

public class Suscripcion {
    private String nombreGimnasio;
    private String fechaIngreso;
    private String fechaPago;
    private String nombrePropietario;

    public Suscripcion() {
        // Constructor vacío requerido para Firestore
    }

    public Suscripcion(String nombreGimnasio, String fechaIngreso, String fechaPago, String nombrePropietario) {
        this.nombreGimnasio = nombreGimnasio;
        this.fechaIngreso = fechaIngreso;
        this.fechaPago = fechaPago;
        this.nombrePropietario = nombrePropietario;
    }

    public String getNombreGimnasio() {
        return nombreGimnasio;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }
}

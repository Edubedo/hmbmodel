package com.example.splash.cuentas;

public class cuenta {

    String cuenta;
    String contrasena;
    String gym;

    public cuenta() {
    }

    public cuenta(String cuenta, String contrasena, String gym) {
        this.cuenta = cuenta;
        this.contrasena = contrasena;
        this.gym = gym;
    }


    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }

}


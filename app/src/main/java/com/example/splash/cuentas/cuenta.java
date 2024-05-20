package com.example.splash.cuentas;

public class cuenta {

    String cuenta;
    String contraseña;
    String gym;
    String rango;

    public cuenta() {
    }

    public cuenta(String cuenta, String contraseña, String gym, String rango) {
        this.cuenta = cuenta;
        this.contraseña = contraseña;
        this.gym = gym;
        this.rango = rango;
    }


    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

}


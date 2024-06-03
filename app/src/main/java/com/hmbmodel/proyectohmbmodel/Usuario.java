package com.hmbmodel.proyectohmbmodel;

public class Usuario {
    private String fullname;
    private String phone;
    private String email;

    public Usuario(String fullname, String phone, String email) {
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}

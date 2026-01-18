package com.jesus.actividad2.dto;

import com.jesus.actividad2.model.Rol;

public class UsuarioResponse {
    private String id;
    private String nombre;
    private String email;
    private Rol rol;

    public UsuarioResponse(String id, String nombre, String email, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public Rol getRol() {
        return rol;
    }
}

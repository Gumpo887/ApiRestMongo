package com.jesus.actividad2.dto;

import jakarta.validation.constraints.NotBlank;

public class InscripcionCreateRequest {
    @NotBlank
    private String camionId;

    @NotBlank
    private String mercanciaId;

    public String getCamionId() {
        return camionId;
    }

    public void setCamionId(String camionId) {
        this.camionId = camionId;
    }

    public String getMercanciaId() {
        return mercanciaId;
    }

    public void setMercanciaId(String mercanciaId) {
        this.mercanciaId = mercanciaId;
    }
}

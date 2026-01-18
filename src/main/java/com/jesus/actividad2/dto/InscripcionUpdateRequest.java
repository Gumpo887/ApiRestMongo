package com.jesus.actividad2.dto;

import com.jesus.actividad2.model.EstadoInscripcion;
import jakarta.validation.constraints.NotNull;

public class InscripcionUpdateRequest {
    @NotNull
    private EstadoInscripcion estado;

    public EstadoInscripcion getEstado() {
        return estado;
    }

    public void setEstado(EstadoInscripcion estado) {
        this.estado = estado;
    }
}

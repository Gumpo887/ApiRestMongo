package com.jesus.actividad2.dto;

import com.jesus.actividad2.model.EstadoInscripcion;
import java.time.LocalDateTime;

public class InscripcionResponse {
    private String id;
    private String camionId;
    private String mercanciaId;
    private LocalDateTime fechaInscripcion;
    private EstadoInscripcion estado;

    public InscripcionResponse(String id, String camionId, String mercanciaId, LocalDateTime fechaInscripcion,
                               EstadoInscripcion estado) {
        this.id = id;
        this.camionId = camionId;
        this.mercanciaId = mercanciaId;
        this.fechaInscripcion = fechaInscripcion;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public String getCamionId() {
        return camionId;
    }

    public String getMercanciaId() {
        return mercanciaId;
    }

    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }

    public EstadoInscripcion getEstado() {
        return estado;
    }
}

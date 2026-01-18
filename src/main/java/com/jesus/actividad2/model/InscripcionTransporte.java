package com.jesus.actividad2.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inscripciones")
public class InscripcionTransporte {
    @Id
    private String id;
    private String camionId;
    private String mercanciaId;
    private LocalDateTime fechaInscripcion;
    private EstadoInscripcion estado;

    public InscripcionTransporte() {
    }

    public InscripcionTransporte(String id, String camionId, String mercanciaId, LocalDateTime fechaInscripcion,
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

    public void setId(String id) {
        this.id = id;
    }

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

    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDateTime fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public EstadoInscripcion getEstado() {
        return estado;
    }

    public void setEstado(EstadoInscripcion estado) {
        this.estado = estado;
    }
}

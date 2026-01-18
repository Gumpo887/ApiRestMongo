package com.jesus.actividad2.dto;

import com.jesus.actividad2.model.EstadoCamion;

public class CamionResponse {
    private String id;
    private String conductorId;
    private String matricula;
    private String modelo;
    private Double capacidadKg;
    private EstadoCamion estado;

    public CamionResponse(String id, String conductorId, String matricula, String modelo, Double capacidadKg,
                          EstadoCamion estado) {
        this.id = id;
        this.conductorId = conductorId;
        this.matricula = matricula;
        this.modelo = modelo;
        this.capacidadKg = capacidadKg;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public String getConductorId() {
        return conductorId;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public Double getCapacidadKg() {
        return capacidadKg;
    }

    public EstadoCamion getEstado() {
        return estado;
    }
}

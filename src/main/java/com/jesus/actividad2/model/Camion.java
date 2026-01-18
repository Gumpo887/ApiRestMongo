package com.jesus.actividad2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "camiones")
public class Camion {
    @Id
    private String id;
    private String conductorId;
    private String matricula;
    private String modelo;
    private Double capacidadKg;
    private EstadoCamion estado;

    public Camion() {
    }

    public Camion(String id, String conductorId, String matricula, String modelo, Double capacidadKg, EstadoCamion estado) {
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

    public void setId(String id) {
        this.id = id;
    }

    public String getConductorId() {
        return conductorId;
    }

    public void setConductorId(String conductorId) {
        this.conductorId = conductorId;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getCapacidadKg() {
        return capacidadKg;
    }

    public void setCapacidadKg(Double capacidadKg) {
        this.capacidadKg = capacidadKg;
    }

    public EstadoCamion getEstado() {
        return estado;
    }

    public void setEstado(EstadoCamion estado) {
        this.estado = estado;
    }
}

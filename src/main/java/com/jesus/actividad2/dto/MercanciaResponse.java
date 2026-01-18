package com.jesus.actividad2.dto;

import com.jesus.actividad2.model.EstadoMercancia;
import java.time.LocalDate;

public class MercanciaResponse {
    private String id;
    private String descripcion;
    private String origen;
    private String destino;
    private Double pesoKg;
    private LocalDate fechaEntregaEstimada;
    private EstadoMercancia estado;

    public MercanciaResponse(String id, String descripcion, String origen, String destino, Double pesoKg,
                             LocalDate fechaEntregaEstimada, EstadoMercancia estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.origen = origen;
        this.destino = destino;
        this.pesoKg = pesoKg;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public Double getPesoKg() {
        return pesoKg;
    }

    public LocalDate getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public EstadoMercancia getEstado() {
        return estado;
    }
}

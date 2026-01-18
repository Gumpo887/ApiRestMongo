package com.jesus.actividad2.dto;

import com.jesus.actividad2.model.EstadoMercancia;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class MercanciaUpdateRequest {
    @NotBlank
    private String descripcion;

    @NotBlank
    private String origen;

    @NotBlank
    private String destino;

    @NotNull
    @Min(0)
    private Double pesoKg;

    @NotNull
    private LocalDate fechaEntregaEstimada;

    @NotNull
    private EstadoMercancia estado;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(Double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public LocalDate getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public void setFechaEntregaEstimada(LocalDate fechaEntregaEstimada) {
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }

    public EstadoMercancia getEstado() {
        return estado;
    }

    public void setEstado(EstadoMercancia estado) {
        this.estado = estado;
    }
}

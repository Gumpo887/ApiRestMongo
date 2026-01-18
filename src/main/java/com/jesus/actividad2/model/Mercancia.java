package com.jesus.actividad2.model;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mercancias")
public class Mercancia {
    @Id
    private String id;
    private String descripcion;
    private String origen;
    private String destino;
    private Double pesoKg;
    private LocalDate fechaEntregaEstimada;
    private EstadoMercancia estado;

    public Mercancia() {
    }

    public Mercancia(String id, String descripcion, String origen, String destino, Double pesoKg,
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

    public void setId(String id) {
        this.id = id;
    }

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

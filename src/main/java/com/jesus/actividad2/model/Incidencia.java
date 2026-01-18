package com.jesus.actividad2.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "incidencias")
public class Incidencia {
    @Id
    private String id;
    private String camionId;
    private String descripcion;
    private LocalDateTime fecha;
    private TipoIncidencia tipo;
    private EstadoIncidencia estado;

    public Incidencia() {
    }

    public Incidencia(String id, String camionId, String descripcion, LocalDateTime fecha, TipoIncidencia tipo,
                      EstadoIncidencia estado) {
        this.id = id;
        this.camionId = camionId;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.tipo = tipo;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public TipoIncidencia getTipo() {
        return tipo;
    }

    public void setTipo(TipoIncidencia tipo) {
        this.tipo = tipo;
    }

    public EstadoIncidencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoIncidencia estado) {
        this.estado = estado;
    }
}

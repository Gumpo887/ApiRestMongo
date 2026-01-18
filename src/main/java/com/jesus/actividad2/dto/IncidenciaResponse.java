package com.jesus.actividad2.dto;

import com.jesus.actividad2.model.EstadoIncidencia;
import com.jesus.actividad2.model.TipoIncidencia;
import java.time.LocalDateTime;

public class IncidenciaResponse {
    private String id;
    private String camionId;
    private String descripcion;
    private LocalDateTime fecha;
    private TipoIncidencia tipo;
    private EstadoIncidencia estado;

    public IncidenciaResponse(String id, String camionId, String descripcion, LocalDateTime fecha,
                              TipoIncidencia tipo, EstadoIncidencia estado) {
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

    public String getCamionId() {
        return camionId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public TipoIncidencia getTipo() {
        return tipo;
    }

    public EstadoIncidencia getEstado() {
        return estado;
    }
}

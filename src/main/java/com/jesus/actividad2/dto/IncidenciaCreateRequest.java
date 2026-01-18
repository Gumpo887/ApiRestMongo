package com.jesus.actividad2.dto;

import com.jesus.actividad2.model.EstadoIncidencia;
import com.jesus.actividad2.model.TipoIncidencia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class IncidenciaCreateRequest {
    @NotBlank
    private String camionId;

    @NotBlank
    private String descripcion;

    @NotNull
    private LocalDateTime fecha;

    @NotNull
    private TipoIncidencia tipo;

    @NotNull
    private EstadoIncidencia estado;

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

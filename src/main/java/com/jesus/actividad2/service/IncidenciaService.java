package com.jesus.actividad2.service;

import com.jesus.actividad2.dto.IncidenciaCreateRequest;
import com.jesus.actividad2.dto.IncidenciaResponse;
import com.jesus.actividad2.dto.IncidenciaUpdateRequest;
import com.jesus.actividad2.exception.ForbiddenException;
import com.jesus.actividad2.exception.NotFoundException;
import com.jesus.actividad2.model.EstadoIncidencia;
import com.jesus.actividad2.model.Incidencia;
import com.jesus.actividad2.model.Rol;
import com.jesus.actividad2.repository.CamionRepository;
import com.jesus.actividad2.repository.IncidenciaRepository;
import com.jesus.actividad2.security.SecurityUtils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class IncidenciaService {
    private final IncidenciaRepository incidenciaRepository;
    private final CamionRepository camionRepository;

    public IncidenciaService(IncidenciaRepository incidenciaRepository, CamionRepository camionRepository) {
        this.incidenciaRepository = incidenciaRepository;
        this.camionRepository = camionRepository;
    }

    public List<IncidenciaResponse> findAll() {
        Rol role = SecurityUtils.currentUserRole();
        if (role == Rol.EMPRESA) {
            return incidenciaRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
        }
        List<String> camionIds = camionRepository.findByConductorId(SecurityUtils.currentUserId()).stream()
                .map(camion -> camion.getId())
                .collect(Collectors.toList());
        return incidenciaRepository.findByCamionIdIn(camionIds).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public IncidenciaResponse findById(String id) {
        Incidencia incidencia = incidenciaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Incidencia no encontrada"));
        validateOwnership(incidencia.getCamionId());
        return toResponse(incidencia);
    }

    public IncidenciaResponse create(IncidenciaCreateRequest request) {
        validateOwnership(request.getCamionId());
        Incidencia incidencia = new Incidencia();
        incidencia.setCamionId(request.getCamionId());
        incidencia.setDescripcion(request.getDescripcion());
        incidencia.setFecha(request.getFecha());
        incidencia.setTipo(request.getTipo());
        incidencia.setEstado(request.getEstado());
        return toResponse(incidenciaRepository.save(incidencia));
    }

    public IncidenciaResponse update(String id, IncidenciaUpdateRequest request) {
        Incidencia incidencia = incidenciaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Incidencia no encontrada"));
        validateOwnership(incidencia.getCamionId());
        incidencia.setDescripcion(request.getDescripcion());
        incidencia.setFecha(request.getFecha());
        incidencia.setTipo(request.getTipo());
        incidencia.setEstado(request.getEstado());
        return toResponse(incidenciaRepository.save(incidencia));
    }

    public void delete(String id) {
        Incidencia incidencia = incidenciaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Incidencia no encontrada"));
        validateOwnership(incidencia.getCamionId());
        incidenciaRepository.delete(incidencia);
    }

    public List<IncidenciaResponse> findActivasUltimoMes(String conductorId) {
        Rol role = SecurityUtils.currentUserRole();
        if (role == Rol.CONDUCTOR && !conductorId.equals(SecurityUtils.currentUserId())) {
            throw new ForbiddenException("No puedes ver incidencias de otro conductor");
        }
        List<String> camionIds = camionRepository.findByConductorId(conductorId).stream()
                .map(camion -> camion.getId())
                .collect(Collectors.toList());
        if (camionIds.isEmpty()) {
            return List.of();
        }
        LocalDateTime desde = LocalDateTime.now().minusMonths(1);
        return incidenciaRepository.findByCamionIdInAndEstadoAndFechaAfter(
                        camionIds, EstadoIncidencia.ABIERTA, desde)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private void validateOwnership(String camionId) {
        if (SecurityUtils.currentUserRole() == Rol.EMPRESA) {
            return;
        }
        String conductorId = SecurityUtils.currentUserId();
        boolean owns = camionRepository.findByConductorId(conductorId).stream()
                .anyMatch(camion -> camion.getId().equals(camionId));
        if (!owns) {
            throw new ForbiddenException("No puedes gestionar incidencias de camiones ajenos");
        }
    }

    private IncidenciaResponse toResponse(Incidencia incidencia) {
        return new IncidenciaResponse(incidencia.getId(), incidencia.getCamionId(), incidencia.getDescripcion(),
                incidencia.getFecha(), incidencia.getTipo(), incidencia.getEstado());
    }
}

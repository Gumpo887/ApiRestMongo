package com.jesus.actividad2.service;

import com.jesus.actividad2.dto.CamionCreateRequest;
import com.jesus.actividad2.dto.CamionResponse;
import com.jesus.actividad2.dto.CamionUpdateRequest;
import com.jesus.actividad2.exception.ForbiddenException;
import com.jesus.actividad2.exception.NotFoundException;
import com.jesus.actividad2.model.Camion;
import com.jesus.actividad2.model.EstadoCamion;
import com.jesus.actividad2.model.Rol;
import com.jesus.actividad2.repository.CamionRepository;
import com.jesus.actividad2.security.SecurityUtils;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CamionService {
    private final CamionRepository camionRepository;

    public CamionService(CamionRepository camionRepository) {
        this.camionRepository = camionRepository;
    }

    public List<CamionResponse> findAll() {
        Rol role = SecurityUtils.currentUserRole();
        if (role == Rol.EMPRESA) {
            return camionRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
        }
        String conductorId = SecurityUtils.currentUserId();
        return camionRepository.findByConductorId(conductorId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CamionResponse findById(String id) {
        Camion camion = camionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Camión no encontrado"));
        validateOwnership(camion);
        return toResponse(camion);
    }

    public CamionResponse create(CamionCreateRequest request) {
        String conductorId = SecurityUtils.currentUserId();
        Camion camion = new Camion();
        camion.setConductorId(conductorId);
        camion.setMatricula(request.getMatricula());
        camion.setModelo(request.getModelo());
        camion.setCapacidadKg(request.getCapacidadKg());
        camion.setEstado(request.getEstado());
        return toResponse(camionRepository.save(camion));
    }

    public CamionResponse update(String id, CamionUpdateRequest request) {
        Camion camion = camionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Camión no encontrado"));
        validateOwnership(camion);
        camion.setMatricula(request.getMatricula());
        camion.setModelo(request.getModelo());
        camion.setCapacidadKg(request.getCapacidadKg());
        camion.setEstado(request.getEstado());
        return toResponse(camionRepository.save(camion));
    }

    public void delete(String id) {
        Camion camion = camionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Camión no encontrado"));
        validateOwnership(camion);
        camionRepository.delete(camion);
    }

    public List<CamionResponse> findDisponiblesByConductor(String conductorId) {
        Rol role = SecurityUtils.currentUserRole();
        if (role == Rol.CONDUCTOR && !conductorId.equals(SecurityUtils.currentUserId())) {
            throw new ForbiddenException("No puedes ver camiones de otro conductor");
        }
        return camionRepository.findByConductorIdAndEstado(conductorId, EstadoCamion.ACTIVO)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private void validateOwnership(Camion camion) {
        Rol role = SecurityUtils.currentUserRole();
        if (role == Rol.CONDUCTOR && !camion.getConductorId().equals(SecurityUtils.currentUserId())) {
            throw new ForbiddenException("No puedes gestionar camiones de otro conductor");
        }
    }

    private CamionResponse toResponse(Camion camion) {
        return new CamionResponse(camion.getId(), camion.getConductorId(), camion.getMatricula(), camion.getModelo(),
                camion.getCapacidadKg(), camion.getEstado());
    }
}

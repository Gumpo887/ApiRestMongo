package com.jesus.actividad2.service;

import com.jesus.actividad2.dto.InscripcionCreateRequest;
import com.jesus.actividad2.dto.InscripcionResponse;
import com.jesus.actividad2.dto.InscripcionUpdateRequest;
import com.jesus.actividad2.exception.ForbiddenException;
import com.jesus.actividad2.exception.NotFoundException;
import com.jesus.actividad2.model.Camion;
import com.jesus.actividad2.model.EstadoInscripcion;
import com.jesus.actividad2.model.InscripcionTransporte;
import com.jesus.actividad2.model.Rol;
import com.jesus.actividad2.repository.CamionRepository;
import com.jesus.actividad2.repository.InscripcionTransporteRepository;
import com.jesus.actividad2.repository.MercanciaRepository;
import com.jesus.actividad2.security.SecurityUtils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class InscripcionTransporteService {
    private final InscripcionTransporteRepository inscripcionRepository;
    private final CamionRepository camionRepository;
    private final MercanciaRepository mercanciaRepository;

    public InscripcionTransporteService(InscripcionTransporteRepository inscripcionRepository,
                                        CamionRepository camionRepository,
                                        MercanciaRepository mercanciaRepository) {
        this.inscripcionRepository = inscripcionRepository;
        this.camionRepository = camionRepository;
        this.mercanciaRepository = mercanciaRepository;
    }

    public List<InscripcionResponse> findAll() {
        return inscripcionRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public InscripcionResponse findById(String id) {
        InscripcionTransporte inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inscripción no encontrada"));
        return toResponse(inscripcion);
    }

    public InscripcionResponse create(InscripcionCreateRequest request) {
        Camion camion = camionRepository.findById(request.getCamionId())
                .orElseThrow(() -> new NotFoundException("Camión no encontrado"));
        String conductorId = SecurityUtils.currentUserId();
        if (!camion.getConductorId().equals(conductorId)) {
            throw new ForbiddenException("No puedes inscribir camiones de otro conductor");
        }
        if (!mercanciaRepository.existsById(request.getMercanciaId())) {
            throw new NotFoundException("Mercancía no encontrada");
        }
        InscripcionTransporte inscripcion = new InscripcionTransporte();
        inscripcion.setCamionId(request.getCamionId());
        inscripcion.setMercanciaId(request.getMercanciaId());
        inscripcion.setFechaInscripcion(LocalDateTime.now());
        inscripcion.setEstado(EstadoInscripcion.PENDIENTE);
        return toResponse(inscripcionRepository.save(inscripcion));
    }

    public InscripcionResponse update(String id, InscripcionUpdateRequest request) {
        InscripcionTransporte inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inscripción no encontrada"));
        validateEmpresa();
        inscripcion.setEstado(request.getEstado());
        return toResponse(inscripcionRepository.save(inscripcion));
    }

    public void delete(String id) {
        if (!inscripcionRepository.existsById(id)) {
            throw new NotFoundException("Inscripción no encontrada");
        }
        validateEmpresa();
        inscripcionRepository.deleteById(id);
    }

    public List<InscripcionResponse> findByMercancia(String mercanciaId) {
        return inscripcionRepository.findByMercanciaId(mercanciaId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public InscripcionResponse aceptar(String id) {
        InscripcionTransporte inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inscripción no encontrada"));
        validateEmpresa();
        inscripcion.setEstado(EstadoInscripcion.ACEPTADA);
        return toResponse(inscripcionRepository.save(inscripcion));
    }

    public InscripcionResponse rechazar(String id) {
        InscripcionTransporte inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inscripción no encontrada"));
        validateEmpresa();
        inscripcion.setEstado(EstadoInscripcion.RECHAZADA);
        return toResponse(inscripcionRepository.save(inscripcion));
    }

    private void validateEmpresa() {
        if (SecurityUtils.currentUserRole() != Rol.EMPRESA) {
            throw new ForbiddenException("Solo EMPRESA puede gestionar inscripciones");
        }
    }

    private InscripcionResponse toResponse(InscripcionTransporte inscripcion) {
        return new InscripcionResponse(inscripcion.getId(), inscripcion.getCamionId(), inscripcion.getMercanciaId(),
                inscripcion.getFechaInscripcion(), inscripcion.getEstado());
    }
}

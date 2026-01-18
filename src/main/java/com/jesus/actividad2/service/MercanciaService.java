package com.jesus.actividad2.service;

import com.jesus.actividad2.dto.MercanciaCreateRequest;
import com.jesus.actividad2.dto.MercanciaResponse;
import com.jesus.actividad2.dto.MercanciaUpdateRequest;
import com.jesus.actividad2.exception.ForbiddenException;
import com.jesus.actividad2.exception.NotFoundException;
import com.jesus.actividad2.model.EstadoInscripcion;
import com.jesus.actividad2.model.EstadoMercancia;
import com.jesus.actividad2.model.Mercancia;
import com.jesus.actividad2.model.Rol;
import com.jesus.actividad2.repository.CamionRepository;
import com.jesus.actividad2.repository.InscripcionTransporteRepository;
import com.jesus.actividad2.repository.MercanciaRepository;
import com.jesus.actividad2.security.SecurityUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class MercanciaService {
    private final MercanciaRepository mercanciaRepository;
    private final CamionRepository camionRepository;
    private final InscripcionTransporteRepository inscripcionRepository;

    public MercanciaService(MercanciaRepository mercanciaRepository, CamionRepository camionRepository,
                            InscripcionTransporteRepository inscripcionRepository) {
        this.mercanciaRepository = mercanciaRepository;
        this.camionRepository = camionRepository;
        this.inscripcionRepository = inscripcionRepository;
    }

    public List<MercanciaResponse> findAll() {
        return mercanciaRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public MercanciaResponse findById(String id) {
        Mercancia mercancia = mercanciaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mercancía no encontrada"));
        return toResponse(mercancia);
    }

    public MercanciaResponse create(MercanciaCreateRequest request) {
        Mercancia mercancia = new Mercancia();
        mercancia.setDescripcion(request.getDescripcion());
        mercancia.setOrigen(request.getOrigen());
        mercancia.setDestino(request.getDestino());
        mercancia.setPesoKg(request.getPesoKg());
        mercancia.setFechaEntregaEstimada(request.getFechaEntregaEstimada());
        mercancia.setEstado(request.getEstado());
        return toResponse(mercanciaRepository.save(mercancia));
    }

    public MercanciaResponse update(String id, MercanciaUpdateRequest request) {
        Mercancia mercancia = mercanciaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mercancía no encontrada"));
        mercancia.setDescripcion(request.getDescripcion());
        mercancia.setOrigen(request.getOrigen());
        mercancia.setDestino(request.getDestino());
        mercancia.setPesoKg(request.getPesoKg());
        mercancia.setFechaEntregaEstimada(request.getFechaEntregaEstimada());
        mercancia.setEstado(request.getEstado());
        return toResponse(mercanciaRepository.save(mercancia));
    }

    public void delete(String id) {
        if (!mercanciaRepository.existsById(id)) {
            throw new NotFoundException("Mercancía no encontrada");
        }
        mercanciaRepository.deleteById(id);
    }

    public List<MercanciaResponse> findDisponibles() {
        return mercanciaRepository.findByEstado(EstadoMercancia.PENDIENTE).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<MercanciaResponse> buscar(String origen, String destino, Double pesoMax) {
        return mercanciaRepository.findAll().stream()
                .filter(m -> origen == null || m.getOrigen().toLowerCase().contains(origen.toLowerCase()))
                .filter(m -> destino == null || m.getDestino().toLowerCase().contains(destino.toLowerCase()))
                .filter(m -> pesoMax == null || m.getPesoKg() <= pesoMax)
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<MercanciaResponse> findTransportadasPorConductor(String conductorId) {
        Rol role = SecurityUtils.currentUserRole();
        if (role == Rol.CONDUCTOR && !conductorId.equals(SecurityUtils.currentUserId())) {
            throw new ForbiddenException("No puedes ver mercancías de otro conductor");
        }
        List<String> camionIds = camionRepository.findByConductorId(conductorId).stream()
                .map(camion -> camion.getId())
                .collect(Collectors.toList());
        if (camionIds.isEmpty()) {
            return List.of();
        }
        Set<String> mercanciaIds = inscripcionRepository
                .findByCamionIdInAndEstado(camionIds, EstadoInscripcion.ACEPTADA)
                .stream()
                .map(inscripcion -> inscripcion.getMercanciaId())
                .collect(Collectors.toCollection(HashSet::new));
        if (mercanciaIds.isEmpty()) {
            return List.of();
        }
        return mercanciaRepository.findAllById(mercanciaIds).stream()
                .filter(mercancia -> mercancia.getEstado() == EstadoMercancia.ASIGNADA
                        || mercancia.getEstado() == EstadoMercancia.ENTREGADA)
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private MercanciaResponse toResponse(Mercancia mercancia) {
        return new MercanciaResponse(mercancia.getId(), mercancia.getDescripcion(), mercancia.getOrigen(),
                mercancia.getDestino(), mercancia.getPesoKg(), mercancia.getFechaEntregaEstimada(),
                mercancia.getEstado());
    }
}

package com.jesus.actividad2.controller;

import com.jesus.actividad2.dto.InscripcionCreateRequest;
import com.jesus.actividad2.dto.InscripcionResponse;
import com.jesus.actividad2.dto.InscripcionUpdateRequest;
import com.jesus.actividad2.service.InscripcionTransporteService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionTransporteController {
    private final InscripcionTransporteService inscripcionService;

    public InscripcionTransporteController(InscripcionTransporteService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('EMPRESA','CONDUCTOR')")
    public ResponseEntity<List<InscripcionResponse>> findAll() {
        return ResponseEntity.ok(inscripcionService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('EMPRESA','CONDUCTOR')")
    public ResponseEntity<InscripcionResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(inscripcionService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('CONDUCTOR')")
    public ResponseEntity<InscripcionResponse> create(@Valid @RequestBody InscripcionCreateRequest request) {
        return ResponseEntity.ok(inscripcionService.create(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('EMPRESA')")
    public ResponseEntity<InscripcionResponse> update(@PathVariable String id,
                                                      @Valid @RequestBody InscripcionUpdateRequest request) {
        return ResponseEntity.ok(inscripcionService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('EMPRESA')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        inscripcionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/mercancia/{mercanciaId}")
    @PreAuthorize("hasAnyRole('EMPRESA','CONDUCTOR')")
    public ResponseEntity<List<InscripcionResponse>> findByMercancia(@PathVariable String mercanciaId) {
        return ResponseEntity.ok(inscripcionService.findByMercancia(mercanciaId));
    }

    @PutMapping("/{id}/aceptar")
    @PreAuthorize("hasRole('EMPRESA')")
    public ResponseEntity<InscripcionResponse> aceptar(@PathVariable String id) {
        return ResponseEntity.ok(inscripcionService.aceptar(id));
    }

    @PutMapping("/{id}/rechazar")
    @PreAuthorize("hasRole('EMPRESA')")
    public ResponseEntity<InscripcionResponse> rechazar(@PathVariable String id) {
        return ResponseEntity.ok(inscripcionService.rechazar(id));
    }
}

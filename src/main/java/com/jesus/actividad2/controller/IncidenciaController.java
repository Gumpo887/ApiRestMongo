package com.jesus.actividad2.controller;

import com.jesus.actividad2.dto.IncidenciaCreateRequest;
import com.jesus.actividad2.dto.IncidenciaResponse;
import com.jesus.actividad2.dto.IncidenciaUpdateRequest;
import com.jesus.actividad2.service.IncidenciaService;
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
@RequestMapping("/api/incidencias")
public class IncidenciaController {
    private final IncidenciaService incidenciaService;

    public IncidenciaController(IncidenciaService incidenciaService) {
        this.incidenciaService = incidenciaService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('EMPRESA','CONDUCTOR')")
    public ResponseEntity<List<IncidenciaResponse>> findAll() {
        return ResponseEntity.ok(incidenciaService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('EMPRESA','CONDUCTOR')")
    public ResponseEntity<IncidenciaResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(incidenciaService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('CONDUCTOR')")
    public ResponseEntity<IncidenciaResponse> create(@Valid @RequestBody IncidenciaCreateRequest request) {
        return ResponseEntity.ok(incidenciaService.create(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CONDUCTOR')")
    public ResponseEntity<IncidenciaResponse> update(@PathVariable String id,
                                                     @Valid @RequestBody IncidenciaUpdateRequest request) {
        return ResponseEntity.ok(incidenciaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CONDUCTOR')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        incidenciaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/conductor/{conductorId}/activas-ultimo-mes")
    @PreAuthorize("hasAnyRole('EMPRESA','CONDUCTOR')")
    public ResponseEntity<List<IncidenciaResponse>> activasUltimoMes(@PathVariable String conductorId) {
        return ResponseEntity.ok(incidenciaService.findActivasUltimoMes(conductorId));
    }
}

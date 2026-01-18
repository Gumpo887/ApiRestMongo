package com.jesus.actividad2.controller;

import com.jesus.actividad2.dto.CamionCreateRequest;
import com.jesus.actividad2.dto.CamionResponse;
import com.jesus.actividad2.dto.CamionUpdateRequest;
import com.jesus.actividad2.service.CamionService;
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
@RequestMapping("/api/camiones")
public class CamionController {
    private final CamionService camionService;

    public CamionController(CamionService camionService) {
        this.camionService = camionService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('EMPRESA','CONDUCTOR')")
    public ResponseEntity<List<CamionResponse>> findAll() {
        return ResponseEntity.ok(camionService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('EMPRESA','CONDUCTOR')")
    public ResponseEntity<CamionResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(camionService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('CONDUCTOR')")
    public ResponseEntity<CamionResponse> create(@Valid @RequestBody CamionCreateRequest request) {
        return ResponseEntity.ok(camionService.create(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CONDUCTOR')")
    public ResponseEntity<CamionResponse> update(@PathVariable String id,
                                                 @Valid @RequestBody CamionUpdateRequest request) {
        return ResponseEntity.ok(camionService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CONDUCTOR')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        camionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/conductor/{conductorId}/disponibles")
    @PreAuthorize("hasAnyRole('EMPRESA','CONDUCTOR')")
    public ResponseEntity<List<CamionResponse>> disponibles(@PathVariable String conductorId) {
        return ResponseEntity.ok(camionService.findDisponiblesByConductor(conductorId));
    }
}

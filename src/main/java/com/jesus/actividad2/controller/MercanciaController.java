package com.jesus.actividad2.controller;

import com.jesus.actividad2.dto.MercanciaCreateRequest;
import com.jesus.actividad2.dto.MercanciaResponse;
import com.jesus.actividad2.dto.MercanciaUpdateRequest;
import com.jesus.actividad2.service.MercanciaService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mercancias")
public class MercanciaController {
    private final MercanciaService mercanciaService;

    public MercanciaController(MercanciaService mercanciaService) {
        this.mercanciaService = mercanciaService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('EMPRESA','CONDUCTOR')")
    public ResponseEntity<List<MercanciaResponse>> findAll() {
        return ResponseEntity.ok(mercanciaService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('EMPRESA','CONDUCTOR')")
    public ResponseEntity<MercanciaResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok(mercanciaService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('EMPRESA')")
    public ResponseEntity<MercanciaResponse> create(@Valid @RequestBody MercanciaCreateRequest request) {
        return ResponseEntity.ok(mercanciaService.create(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('EMPRESA')")
    public ResponseEntity<MercanciaResponse> update(@PathVariable String id,
                                                    @Valid @RequestBody MercanciaUpdateRequest request) {
        return ResponseEntity.ok(mercanciaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('EMPRESA')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        mercanciaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disponibles")
    @PreAuthorize("hasAnyRole('EMPRESA','CONDUCTOR')")
    public ResponseEntity<List<MercanciaResponse>> disponibles() {
        return ResponseEntity.ok(mercanciaService.findDisponibles());
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasAnyRole('EMPRESA','CONDUCTOR')")
    public ResponseEntity<List<MercanciaResponse>> buscar(@RequestParam(required = false) String origen,
                                                          @RequestParam(required = false) String destino,
                                                          @RequestParam(required = false) Double pesoMax) {
        return ResponseEntity.ok(mercanciaService.buscar(origen, destino, pesoMax));
    }

    @GetMapping("/conductor/{conductorId}/transportadas")
    @PreAuthorize("hasAnyRole('EMPRESA','CONDUCTOR')")
    public ResponseEntity<List<MercanciaResponse>> transportadas(@PathVariable String conductorId) {
        return ResponseEntity.ok(mercanciaService.findTransportadasPorConductor(conductorId));
    }
}

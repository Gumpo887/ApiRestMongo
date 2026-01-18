package com.jesus.actividad2.repository;

import com.jesus.actividad2.model.EstadoInscripcion;
import com.jesus.actividad2.model.InscripcionTransporte;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InscripcionTransporteRepository extends MongoRepository<InscripcionTransporte, String> {
    List<InscripcionTransporte> findByMercanciaId(String mercanciaId);
    List<InscripcionTransporte> findByCamionIdIn(List<String> camionIds);
    List<InscripcionTransporte> findByCamionIdInAndEstado(List<String> camionIds, EstadoInscripcion estado);
}

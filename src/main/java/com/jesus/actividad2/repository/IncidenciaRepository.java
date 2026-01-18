package com.jesus.actividad2.repository;

import com.jesus.actividad2.model.EstadoIncidencia;
import com.jesus.actividad2.model.Incidencia;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IncidenciaRepository extends MongoRepository<Incidencia, String> {
    List<Incidencia> findByCamionIdIn(List<String> camionIds);
    List<Incidencia> findByCamionIdInAndEstadoAndFechaAfter(
            List<String> camionIds, EstadoIncidencia estado, LocalDateTime fecha);
}

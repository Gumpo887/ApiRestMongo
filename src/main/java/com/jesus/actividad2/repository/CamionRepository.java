package com.jesus.actividad2.repository;

import com.jesus.actividad2.model.Camion;
import com.jesus.actividad2.model.EstadoCamion;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CamionRepository extends MongoRepository<Camion, String> {
    List<Camion> findByConductorId(String conductorId);
    List<Camion> findByConductorIdAndEstado(String conductorId, EstadoCamion estado);
}

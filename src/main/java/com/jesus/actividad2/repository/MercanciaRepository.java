package com.jesus.actividad2.repository;

import com.jesus.actividad2.model.EstadoMercancia;
import com.jesus.actividad2.model.Mercancia;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MercanciaRepository extends MongoRepository<Mercancia, String> {
    List<Mercancia> findByEstado(EstadoMercancia estado);
    List<Mercancia> findByOrigenContainingIgnoreCase(String origen);
    List<Mercancia> findByDestinoContainingIgnoreCase(String destino);
    List<Mercancia> findByPesoKgLessThanEqual(Double pesoKg);
}

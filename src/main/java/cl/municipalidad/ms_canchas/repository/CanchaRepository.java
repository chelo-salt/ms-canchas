package cl.municipalidad.ms_canchas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.municipalidad.ms_canchas.model.CanchaModel;

@Repository
public interface CanchaRepository extends JpaRepository<CanchaModel, Long> {
    // Hereda todos los métodos CRUD básicos
}
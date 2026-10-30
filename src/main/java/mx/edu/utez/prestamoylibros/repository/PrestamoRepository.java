package mx.edu.utez.prestamoylibros.repository;

import mx.edu.utez.prestamoylibros.model.Prestamo.Estado;
import mx.edu.utez.prestamoylibros.model.Prestamo.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    Prestamo[] findByLectorIdAndEstadoIn(Long lectorId, List<Estado> estados);
}

package mx.edu.utez.prestamoylibros.repository;

import mx.edu.utez.prestamoylibros.model.Prestamo.Estado;
import mx.edu.utez.prestamoylibros.model.Prestamo.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    Prestamo[] findByLector_Id(Long idLector);

    Prestamo[] findAllByLector_IdOrEstado_DevueltoOrEstado(Long lectorId, Estado estado);

    Prestamo[] findAllByLector_IdAndEstado_Prestado(Long lectorId);

    Prestamo[] findByLectorIdAndEstado(Long lectorId, Estado attr0);
}

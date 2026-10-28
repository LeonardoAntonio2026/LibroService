package mx.edu.utez.prestamoylibros.repository;

import mx.edu.utez.prestamoylibros.model.Libro;
import mx.edu.utez.prestamoylibros.model.Prestamo.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

}

package mx.edu.utez.prestamoylibros.model.Prestamo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.prestamoylibros.model.Lector;
import mx.edu.utez.prestamoylibros.model.Libro;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Table(name="prestamo")
@Getter
@Setter
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Lector lector;

    @ManyToOne
    private Libro libro;

    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionMaxima;

    @Enumerated(EnumType.STRING)
    private Estado estado;
}

package mx.edu.utez.prestamoylibros.service;

import mx.edu.utez.prestamoylibros.dto.ModificarLibroDto;
import mx.edu.utez.prestamoylibros.dto.SolicitarPrestamoDto;
import mx.edu.utez.prestamoylibros.exception.CustomException;
import mx.edu.utez.prestamoylibros.model.Lector;
import mx.edu.utez.prestamoylibros.model.Libro;
import mx.edu.utez.prestamoylibros.model.Prestamo.Estado;
import mx.edu.utez.prestamoylibros.model.Prestamo.Prestamo;
import mx.edu.utez.prestamoylibros.repository.LectorRepository;
import mx.edu.utez.prestamoylibros.repository.LibroRepository;
import mx.edu.utez.prestamoylibros.repository.PrestamoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class PrestamoService {

    final LibroRepository libroRepository;
    final LectorRepository lectorRepository;
    final PrestamoRepository prestamoRepository;

    public PrestamoService(LibroRepository libroRepository, LectorRepository lectorRepository, PrestamoRepository prestamoRepository) {
        this.libroRepository = libroRepository;
        this.lectorRepository = lectorRepository;
        this.prestamoRepository = prestamoRepository;
    }


    public Prestamo SolicitarPrestamo(SolicitarPrestamoDto dto){
        Lector lectorActual = lectorRepository.findById(dto.getIdLector()).orElseThrow(() -> new CustomException("No exsite un usuario con ese id"));

        if(!lectorActual.isActivo()){
            throw new CustomException("El usuario no esta activo");
        }

        Libro libroPrestamo = libroRepository.findById(dto.getIdLibro()).orElseThrow(() -> new CustomException("No se encontro ese libro"));

        if(libroPrestamo.getEjemplaresDisponibles() < 1){
            throw new CustomException("No hay suficientes ejemplares de ese libro");
        }

        Prestamo[] prestamosActivos = prestamoRepository.findByLectorIdAndEstadoIn(dto.getIdLector(), List.of(Estado.PRESTADO, Estado.VENCIDO));
        int limitePrestamos = lectorActual.isMembresiaPremium() ? 5 : 3;

        if(prestamosActivos.length >= limitePrestamos){
            throw new CustomException("El lector ya tiene " + limitePrestamos + " prestamos activos");
        }

        libroPrestamo.setEjemplaresDisponibles(libroPrestamo.getEjemplaresDisponibles() -1 );

        Prestamo nuevoPrestamo = new Prestamo();
        nuevoPrestamo.setLector(lectorActual);
        nuevoPrestamo.setLibro(libroPrestamo);
        nuevoPrestamo.setEstado(Estado.PRESTADO);
        nuevoPrestamo.setFechaPrestamo(LocalDate.now());
        nuevoPrestamo.setFechaDevolucionMaxima(LocalDate.now().plusDays(14));

        return prestamoRepository.save(nuevoPrestamo);

    }

    public Prestamo DevolverLibro(ModificarLibroDto dto){
        Prestamo prestamoActual = prestamoRepository.findById(dto.getPrestamoId()).orElseThrow(() -> new CustomException("No existe ese prestamo"));
        if(prestamoActual.getEstado() == Estado.PRESTADO || prestamoActual.getEstado() == Estado.VENCIDO){
            prestamoActual.setEstado(Estado.DEVUELTO);

            Libro libroActual = libroRepository.findById(prestamoActual.getLibro().getId()).orElseThrow(() -> new CustomException("No se encontro el libro"));
            libroActual.setEjemplaresDisponibles(libroActual.getEjemplaresDisponibles() + 1);

            if(prestamoActual.getFechaDevolucionMaxima().isBefore(LocalDate.now())){
                Lector lectorActual = lectorRepository.findById(prestamoActual.getLector().getId()).orElseThrow(() -> new CustomException("No se encontro un lector"));
                lectorActual.setActivo(false);

            }

            return prestamoRepository.save(prestamoActual);

        } else{
            throw new CustomException("Ese libro ya se entrego");
        }



    }

    public Prestamo RenovarPrestamo(ModificarLibroDto dto) {
        Prestamo prestamoActual = prestamoRepository.findById(dto.getPrestamoId()).orElseThrow(() -> new CustomException("No existe ese prestamo"));

        if (prestamoActual.getEstado() == Estado.PRESTADO) {
            prestamoActual.setFechaDevolucionMaxima(prestamoActual.getFechaDevolucionMaxima().plusDays(7));
            return prestamoRepository.save(prestamoActual);
        } else {
            throw new CustomException("El prestamo no esta disponible para renovar");
        }
    }
}

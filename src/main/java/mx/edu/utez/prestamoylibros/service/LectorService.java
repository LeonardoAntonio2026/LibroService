package mx.edu.utez.prestamoylibros.service;

import mx.edu.utez.prestamoylibros.dto.LectorDto;
import mx.edu.utez.prestamoylibros.exception.CustomException;
import mx.edu.utez.prestamoylibros.model.Lector;
import mx.edu.utez.prestamoylibros.model.Libro;
import mx.edu.utez.prestamoylibros.model.Prestamo.Estado;
import mx.edu.utez.prestamoylibros.model.Prestamo.Prestamo;
import mx.edu.utez.prestamoylibros.repository.LectorRepository;
import mx.edu.utez.prestamoylibros.repository.LibroRepository;
import mx.edu.utez.prestamoylibros.repository.PrestamoRepository;

public class LectorService {
    final LibroRepository libroRepository;
    final LectorRepository lectorRepository;
    final PrestamoRepository prestamoRepository;

    public LectorService(LibroRepository libroRepository, LectorRepository lectorRepository, PrestamoRepository prestamoRepository) {
        this.libroRepository = libroRepository;
        this.lectorRepository = lectorRepository;
        this.prestamoRepository = prestamoRepository;
    }

    public Lector RehabilitarLector(LectorDto dto){
        Lector lectorActual = lectorRepository.findById(dto.getIdLector()).orElseThrow(() -> new CustomException("No se encontro un lector"));
        lectorActual.setActivo(true);
        return lectorRepository.save(lectorActual);
    }

    public Prestamo[] listarLibrosPrestados(LectorDto dto){
        Lector lectorActual = lectorRepository.findById(dto.getIdLector()).orElseThrow(() -> new CustomException("No se encontro un lector"));

        return prestamoRepository.findByLectorIdAndEstado(lectorActual.getId(), Estado.PRESTADO);
    }
}

package mx.edu.utez.prestamoylibros.controller;

import mx.edu.utez.prestamoylibros.dto.LectorDto;
import mx.edu.utez.prestamoylibros.model.Lector;
import mx.edu.utez.prestamoylibros.model.Prestamo.Prestamo;
import mx.edu.utez.prestamoylibros.service.LectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lector")
@CrossOrigin(origins = "*")
public class LectorController {

    private final LectorService lectorService;

    public LectorController(LectorService lectorService) {
        this.lectorService = lectorService;
    }

    @PutMapping("/rehabilitar")
    public ResponseEntity<Lector> rehabilitarLector(@RequestBody LectorDto dto) {
        Lector lectorRehabilitado = lectorService.RehabilitarLector(dto);
        return new ResponseEntity<>(lectorRehabilitado, HttpStatus.OK);
    }

    @PostMapping("/libros-prestados")
    public ResponseEntity<Prestamo[]> listarLibrosPrestados(@RequestBody LectorDto dto) {
        Prestamo[] prestamos = lectorService.listarLibrosPrestados(dto);
        return new ResponseEntity<>(prestamos, HttpStatus.OK);
    }
}

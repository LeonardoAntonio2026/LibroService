package mx.edu.utez.prestamoylibros.controller;

import mx.edu.utez.prestamoylibros.dto.ModificarLibroDto;
import mx.edu.utez.prestamoylibros.dto.SolicitarPrestamoDto;
import mx.edu.utez.prestamoylibros.model.Prestamo.Prestamo;
import mx.edu.utez.prestamoylibros.service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prestamo")
@CrossOrigin(origins = "*")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping("/solicitar")
    public ResponseEntity<Prestamo> solicitarPrestamo(@Valid @RequestBody SolicitarPrestamoDto dto) {
        Prestamo nuevoPrestamo = prestamoService.SolicitarPrestamo(dto);
        return new ResponseEntity<>(nuevoPrestamo, HttpStatus.CREATED);
    }

    @PutMapping("/devolver")
    public ResponseEntity<Prestamo> devolverLibro(@Valid @RequestBody ModificarLibroDto dto) {
        Prestamo prestamoDevuelto = prestamoService.DevolverLibro(dto);
        return new ResponseEntity<>(prestamoDevuelto, HttpStatus.OK);
    }

    @PutMapping("/renovar")
    public ResponseEntity<Prestamo> renovarPrestamo(@Valid @RequestBody ModificarLibroDto dto) {
        Prestamo prestamoRenovado = prestamoService.RenovarPrestamo(dto);
        return new ResponseEntity<>(prestamoRenovado, HttpStatus.OK);
    }
}

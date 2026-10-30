package mx.edu.utez.prestamoylibros.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ModificarLibroDto {
    @NotNull(message = "El id del prestamo es obligatorio")
    @Positive(message = "El id del prestamo debe ser mayor a 0")
    public Long prestamoId;
}

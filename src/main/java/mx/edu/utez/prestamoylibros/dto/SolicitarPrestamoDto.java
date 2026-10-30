package mx.edu.utez.prestamoylibros.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SolicitarPrestamoDto {
    @NotNull(message = "El id del lector es obligatorio")
    @Positive(message = "El id del lector debe ser mayor a 0")
    public Long idLector;

    @NotNull(message = "El id del libro es obligatorio")
    @Positive(message = "El id del libro debe ser mayor a 0")
    public Long idLibro;

}

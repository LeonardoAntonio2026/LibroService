package mx.edu.utez.prestamoylibros.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class LectorDto {
    @NotNull(message = "El id del lector es obligatorio")
    @Positive(message = "El id del lector debe ser mayor a 0")
    public Long idLector;
}

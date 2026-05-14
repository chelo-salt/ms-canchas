package cl.municipalidad.ms_canchas.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CanchaRequest {
    @NotBlank(message = "El nombre de la cancha es obligatorio")
    private String nombre;

    @NotBlank(message = "El tipo (TENIS/FUTBOLITO) es obligatorio")
    private String tipo;

    @NotNull(message = "El ID del recinto es obligatorio")
    private Long idRecinto;

    @Positive(message = "El precio debe ser mayor a 0")
    private Double precioHora;
}
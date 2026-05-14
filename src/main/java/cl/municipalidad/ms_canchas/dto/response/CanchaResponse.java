package cl.municipalidad.ms_canchas.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CanchaResponse {
    private Long id;
    private String nombre;
    private String tipo;
    private Long idRecinto;
    private Double precioHora;
}
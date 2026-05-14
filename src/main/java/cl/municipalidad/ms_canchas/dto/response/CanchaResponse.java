package cl.municipalidad.ms_canchas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor // Agrégalos por seguridad
@AllArgsConstructor
public class CanchaResponse {
    private Long id;
    private String nombre;
    private String tipo;
    private Double precioHora;
    private Long idRecinto;
    private String nombreRecinto; // <--- Revisa que esté exactamente así
}
package cl.municipalidad.ms_canchas.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecintoDTO {
    private Long idRecinto; // O simplemente 'id', como lo tengas en ms-recintos
    private String nombre;
    private String direccion;
}
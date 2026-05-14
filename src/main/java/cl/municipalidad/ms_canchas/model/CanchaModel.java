package cl.municipalidad.ms_canchas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "canchas")
@Data
public class CanchaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCancha;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String tipo; // Ejemplo: TENIS, FUTBOLITO

    @Column(name = "id_recinto", nullable = false)
    private Long idRecinto; 

    @Column(nullable = false)
    private Double precioHora;
}
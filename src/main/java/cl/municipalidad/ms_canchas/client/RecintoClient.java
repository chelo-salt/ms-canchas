package cl.municipalidad.ms_canchas.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import cl.municipalidad.ms_canchas.dto.response.RecintoDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RecintoClient {

    private final WebClient.Builder webClientBuilder;

    /**
     * Valida si un recinto existe enviando el token de seguridad.
     */
    public boolean existeRecinto(Long idRecinto, String token) {
        try {
            webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/api/v1/recintos/" + idRecinto)
                .header("Authorization", token) // Inyectamos el token para que ms-recintos nos deje pasar
                .retrieve()
                .toBodilessEntity()
                .block();
            return true;
        } catch (Exception e) {
            System.err.println("Error validando existencia de recinto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene los datos completos de un recinto enviando el token de seguridad.
     */
    public RecintoDTO obtenerPorId(Long idRecinto, String token) {
        try {
            return webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/api/v1/recintos/" + idRecinto)
                .header("Authorization", token) // Inyectamos el token aquí también
                .retrieve()
                .bodyToMono(RecintoDTO.class)
                .block();
        } catch (Exception e) {
            System.err.println("Error obteniendo datos del recinto: " + e.getMessage());
            return null;
        }
    }
}
package cl.municipalidad.ms_canchas.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RecintoClient {

    private final WebClient.Builder webClientBuilder;

    public boolean existeRecinto(Long idRecinto) {
        try {
            // Llamamos al microservicio 1 (puerto 8081)
            webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/api/v1/recintos/" + idRecinto)
                .retrieve()
                .toBodilessEntity()
                .block(); // .block() hace que la llamada sea síncrona (esperamos la respuesta)
            
            return true; // Si responde 200 OK, el recinto existe
        } catch (Exception e) {
            // Si el otro microservicio responde 404 o está apagado, entra aquí
            return false;
        }
    }
}
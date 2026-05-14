package cl.municipalidad.ms_canchas.service;

import org.springframework.stereotype.Service;
import cl.municipalidad.ms_canchas.client.RecintoClient; 
import cl.municipalidad.ms_canchas.dto.request.CanchaRequest;
import cl.municipalidad.ms_canchas.dto.response.CanchaResponse;
import cl.municipalidad.ms_canchas.model.CanchaModel;
import cl.municipalidad.ms_canchas.repository.CanchaRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CanchaService {

    private final CanchaRepository canchaRepository;
    private final RecintoClient recintoClient; 

    public CanchaResponse guardar(CanchaRequest request) {
        
        // --- LA VALIDACIÓN MÁGICA ---
        // Verifica si el recinto existe llamando al otro microservicio (ms-recintos)
        if (!recintoClient.existeRecinto(request.getIdRecinto(), null)) {
            throw new RuntimeException("Error: El recinto con ID " + request.getIdRecinto() + " no existe en el sistema.");
        }

        CanchaModel modelo = new CanchaModel();
        modelo.setNombre(request.getNombre());
        modelo.setTipo(request.getTipo());
        modelo.setIdRecinto(request.getIdRecinto());
        modelo.setPrecioHora(request.getPrecioHora());

        CanchaModel guardado = canchaRepository.save(modelo);
        return mapearADto(guardado);
    }

    public List<CanchaResponse> obtenerTodas() {
        return canchaRepository.findAll().stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    private CanchaResponse mapearADto(CanchaModel modelo) {
        // 1. Intentamos obtener la info extra del recinto
        // Usamos el nuevo método de tu RecintoClient
        var recintoInfo = recintoClient.obtenerPorId(modelo.getIdRecinto(), null);
        
        // 2. Si el recinto existe, sacamos el nombre, si no, ponemos un mensaje
        String nombreRecinto = (recintoInfo != null) ? recintoInfo.getNombre() : "Recinto no encontrado";

        return CanchaResponse.builder()
                .id(modelo.getIdCancha()) 
                .nombre(modelo.getNombre())
                .tipo(modelo.getTipo())
                .idRecinto(modelo.getIdRecinto())
                .nombreRecinto(nombreRecinto) // <-- Solo añadimos esta línea nueva
                .precioHora(modelo.getPrecioHora())
                .build();
    }
}
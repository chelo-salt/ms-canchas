package cl.municipalidad.ms_canchas.controller;

import cl.municipalidad.ms_canchas.dto.request.CanchaRequest;
import cl.municipalidad.ms_canchas.dto.response.CanchaResponse;
import cl.municipalidad.ms_canchas.service.CanchaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/canchas")
@RequiredArgsConstructor
public class CanchaController {

    private final CanchaService canchaService;

    @GetMapping
    public ResponseEntity<List<CanchaResponse>> listar() {
        return ResponseEntity.ok(canchaService.obtenerTodas());
    }

    @PostMapping
    public ResponseEntity<CanchaResponse> crear(@Valid @RequestBody CanchaRequest request) {
        return new ResponseEntity<>(canchaService.guardar(request), HttpStatus.CREATED);
    }
}
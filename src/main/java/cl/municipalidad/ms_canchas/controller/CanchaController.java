package cl.municipalidad.ms_canchas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cl.municipalidad.ms_canchas.dto.request.CanchaRequest;
import cl.municipalidad.ms_canchas.dto.response.CanchaResponse;
import cl.municipalidad.ms_canchas.service.CanchaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/v1/canchas")
@RequiredArgsConstructor
public class CanchaController {

    private final CanchaService canchaService;

    @PostMapping
    public ResponseEntity<CanchaResponse> crear(@Valid @RequestBody CanchaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(canchaService.guardar(request));
    }

    @GetMapping
    public ResponseEntity<List<CanchaResponse>> listar() {
        return ResponseEntity.ok(canchaService.obtenerTodas());
    }
}
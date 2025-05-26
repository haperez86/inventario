package com.nexos.inventario.controller;

import com.nexos.inventario.dto.MercanciaDTO;
import com.nexos.inventario.service.MercanciaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/mercancias")
public class MercanciaController {

    private final MercanciaService mercanciaService;

    public MercanciaController(MercanciaService mercanciaService) {
        this.mercanciaService = mercanciaService;
    }

    // Crear mercancía
    @PostMapping
    public ResponseEntity<MercanciaDTO> crear(@RequestBody MercanciaDTO dto) {
        return ResponseEntity.ok(mercanciaService.crearMercancia(dto));
    }

    // Editar mercancía
    @PutMapping("/{id}")
    public ResponseEntity<MercanciaDTO> editar(@PathVariable Long id, @RequestBody MercanciaDTO dto) {
        return ResponseEntity.ok(mercanciaService.editarMercancia(id, dto));
    }

    // Eliminar mercancía
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id, @RequestParam Long idUsuario) {
        mercanciaService.eliminarMercancia(id, idUsuario);
        return ResponseEntity.noContent().build();
    }

    // Filtrar mercancías
    @GetMapping("/buscar")
    public ResponseEntity<List<MercanciaDTO>> filtrar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Long idUsuario,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaIngreso
    ) {
        return ResponseEntity.ok(mercanciaService.filtrar(nombre, idUsuario, fechaIngreso));
    }

    @GetMapping
    public ResponseEntity<List<MercanciaDTO>> listar() {
        return ResponseEntity.ok(mercanciaService.listarTodas());
    }
}

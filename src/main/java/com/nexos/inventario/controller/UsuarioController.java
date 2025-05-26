package com.nexos.inventario.controller;

import com.nexos.inventario.dto.UsuarioDTO;
import com.nexos.inventario.model.Usuario;
import com.nexos.inventario.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<UsuarioDTO> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> crear(@RequestBody UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEdad(dto.getEdad());
        usuario.setCargo(dto.getCargo());
        usuario.setFechaIngreso(dto.getFechaIngreso());

        Usuario guardado = usuarioRepository.save(usuario);
        UsuarioDTO respuesta = convertirADTO(guardado);

        return ResponseEntity.created(URI.create("/api/usuarios/" + guardado.getId()))
                .body(respuesta);
    }

    private UsuarioDTO convertirADTO(Usuario u) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(u.getId());
        dto.setNombre(u.getNombre());
        dto.setEdad(u.getEdad());
        dto.setCargo(u.getCargo());
        dto.setFechaIngreso(u.getFechaIngreso());
        return dto;
    }
}

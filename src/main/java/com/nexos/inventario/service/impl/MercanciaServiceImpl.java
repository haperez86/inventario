package com.nexos.inventario.service.impl;

import com.nexos.inventario.dto.MercanciaDTO;
import com.nexos.inventario.exception.BusinessException;
import com.nexos.inventario.model.Mercancia;
import com.nexos.inventario.model.Usuario;
import com.nexos.inventario.repository.MercanciaRepository;
import com.nexos.inventario.repository.UsuarioRepository;
import com.nexos.inventario.service.MercanciaService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MercanciaServiceImpl implements MercanciaService {

    private final MercanciaRepository mercanciaRepository;
    private final UsuarioRepository usuarioRepository;

    public MercanciaServiceImpl(MercanciaRepository mercanciaRepository, UsuarioRepository usuarioRepository) {
        this.mercanciaRepository = mercanciaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public MercanciaDTO crearMercancia(MercanciaDTO dto) {
        if (mercanciaRepository.findByNombre(dto.getNombre()).isPresent()) {
            throw new BusinessException("Ya existe una mercancía con ese nombre.");
        }

        if (dto.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new BusinessException("La fecha de ingreso no puede ser futura.");
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioRegistroId())
                .orElseThrow(() -> new BusinessException("Usuario no encontrado."));

        Mercancia m = new Mercancia();
        m.setNombre(dto.getNombre());
        m.setCantidad(dto.getCantidad());
        m.setFechaIngreso(dto.getFechaIngreso());
        m.setUsuarioRegistro(usuario);

        Mercancia guardada = mercanciaRepository.save(m);
        dto.setId(guardada.getId());
        return dto;
    }

    @Override
    public MercanciaDTO editarMercancia(Long id, MercanciaDTO dto) {
        Mercancia existente = mercanciaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Mercancía no encontrada."));

        // Validar que la fecha de ingreso no sea futura (como en crear)
        if (dto.getFechaIngreso() != null && dto.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new BusinessException("La fecha de ingreso no puede ser futura.");
        }

        // Validar usuario modificador
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioModificacionId())
                .orElseThrow(() -> new BusinessException("Usuario no encontrado."));

        // Validar que el nombre no exista en otra mercancía distinta
        if (!dto.getNombre().equalsIgnoreCase(existente.getNombre())) {
            if (mercanciaRepository.findByNombre(dto.getNombre()).isPresent()) {
                throw new BusinessException("Ya existe una mercancía con ese nombre.");
            }
        }

        // Actualizar campos
        existente.setNombre(dto.getNombre());
        existente.setCantidad(dto.getCantidad());
        existente.setFechaIngreso(dto.getFechaIngreso());

        // Registrar usuario que modifica y fecha de modificación
        existente.setUsuarioModificacion(usuario);
        existente.setFechaModificacion(LocalDate.now());

        mercanciaRepository.save(existente);

        dto.setId(existente.getId());
        return dto;
    }

    @Override
    public void eliminarMercancia(Long id, Long idUsuarioSolicitante) {
        Mercancia m = mercanciaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Mercancía no encontrada."));

        if (!m.getUsuarioRegistro().getId().equals(idUsuarioSolicitante)) {
            throw new BusinessException("Solo el usuario que registró la mercancía puede eliminarla.");
        }

        mercanciaRepository.deleteById(id);
    }

    @Override
    public List<MercanciaDTO> filtrar(String nombre, Long idUsuario, LocalDate fechaIngreso) {
        List<Mercancia> mercancias = mercanciaRepository.findAll();

        return mercancias.stream()
                .filter(m ->
                        (nombre == null || m.getNombre().equalsIgnoreCase(nombre)) &&
                                (idUsuario == null || m.getUsuarioRegistro().getId().equals(idUsuario)) &&
                                (fechaIngreso == null || m.getFechaIngreso().equals(fechaIngreso))
                )
                .map(m -> {
                    MercanciaDTO dto = new MercanciaDTO();
                    dto.setId(m.getId());
                    dto.setNombre(m.getNombre());
                    dto.setCantidad(m.getCantidad());
                    dto.setFechaIngreso(m.getFechaIngreso());
                    dto.setUsuarioRegistroId(m.getUsuarioRegistro().getId());
                    dto.setUsuarioModificacionId(
                            m.getUsuarioModificacion() != null ? m.getUsuarioModificacion().getId() : null
                    );
                    dto.setFechaModificacion(m.getFechaModificacion());
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<MercanciaDTO> listarTodas() {
        List<Mercancia> mercancias = mercanciaRepository.findAll();

        return mercancias.stream().map(m -> {
            MercanciaDTO dto = new MercanciaDTO();
            dto.setId(m.getId());
            dto.setNombre(m.getNombre());
            dto.setCantidad(m.getCantidad());
            dto.setFechaIngreso(m.getFechaIngreso());
            dto.setUsuarioRegistroId(m.getUsuarioRegistro() != null ? m.getUsuarioRegistro().getId() : null);
            dto.setUsuarioModificacionId(m.getUsuarioModificacion() != null ? m.getUsuarioModificacion().getId() : null);
            dto.setFechaModificacion(m.getFechaModificacion());
            return dto;
        }).collect(Collectors.toList());
    }
}

package com.nexos.inventario.service;

import com.nexos.inventario.dto.MercanciaDTO;

import java.time.LocalDate;
import java.util.List;

public interface MercanciaService {
    MercanciaDTO crearMercancia(MercanciaDTO dto);
    MercanciaDTO editarMercancia(Long id, MercanciaDTO dto);
    void eliminarMercancia(Long id, Long idUsuarioSolicitante);
    List<MercanciaDTO> filtrar(String nombre, Long idUsuario, LocalDate fechaIngreso);
    List<MercanciaDTO> listarTodas();
}

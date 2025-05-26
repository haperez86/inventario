package com.nexos.inventario.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MercanciaDTO {
    private Long id;
    private String nombre;
    private int cantidad;
    private LocalDate fechaIngreso;
    private Long usuarioRegistroId;
    private Long usuarioModificacionId;
    private LocalDate fechaModificacion;
}

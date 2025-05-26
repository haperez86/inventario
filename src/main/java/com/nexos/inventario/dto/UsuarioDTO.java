package com.nexos.inventario.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private int edad;
    private String cargo;
    private LocalDate fechaIngreso;
}
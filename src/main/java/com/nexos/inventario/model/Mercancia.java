package com.nexos.inventario.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "mercancias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mercancia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int cantidad;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @ManyToOne
    @JoinColumn(name = "usuario_registro_id")
    private Usuario usuarioRegistro;

    @ManyToOne
    @JoinColumn(name = "usuario_modificacion_id")
    private Usuario usuarioModificacion;

    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;
}
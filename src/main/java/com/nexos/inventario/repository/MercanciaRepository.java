package com.nexos.inventario.repository;

import com.nexos.inventario.model.Mercancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MercanciaRepository extends JpaRepository<Mercancia, Long> {
    Optional<Mercancia> findByNombre(String nombre);
}
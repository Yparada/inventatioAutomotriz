package com.example.repository;

import com.example.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository <Cargo,Long> {
    public Cargo findByNombre(String nombre);
}

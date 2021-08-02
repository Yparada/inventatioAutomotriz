package com.example.repository;

import com.example.entity.Cargo;
import com.example.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    public List<Usuario> findByNombre(String nombre);
    public List<Usuario> findByCargo(Cargo cargo);
}

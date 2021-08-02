package com.example.repository;

import com.example.entity.Producto;
import com.example.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
    public Producto findByNombre(String nombre);
    public Producto findByIdProducto(Long id);
    public List<Producto> findByFechaIngreso(LocalDate date);
    public List<Producto> findByUsuarioRegistra(Usuario usuario);
}

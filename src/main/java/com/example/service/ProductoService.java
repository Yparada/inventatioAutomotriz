package com.example.service;

import com.example.entity.Producto;
import com.example.entity.Usuario;

import java.time.LocalDate;
import java.util.List;

public interface ProductoService {
    public List<Producto> getAllProducto();
    public Producto getProductoById(Long id);
    public Producto getProductoByNombre(String nombre);
    public List<Producto> getFechaIngreso(LocalDate fechaIngreso);
    public List<Producto> getProductoByUsuario(Usuario usuario);
    public Producto createProducto(Producto producto);
    public Producto updateProducto (Producto producto);
    public Producto deleteProducto (Long id);
}

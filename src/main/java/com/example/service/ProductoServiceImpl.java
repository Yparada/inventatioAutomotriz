package com.example.service;

import com.example.entity.Producto;
import com.example.entity.Usuario;
import com.example.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> getAllProducto() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getProductoById(Long id) {
        return productoRepository.findByIdProducto(id);
    }

    @Override
    public Producto getProductoByNombre(String nombre) {
        return productoRepository.findByNombre(nombre);
    }

    @Override
    public List<Producto> getFechaIngreso(LocalDate fechaIngreso) {
        return productoRepository.findByFechaIngreso(fechaIngreso);
    }

    @Override
    public List<Producto> getProductoByUsuario(Usuario usuario) {
        return productoRepository.findByUsuarioRegistra(usuario);
    }

    @Override
    public Producto createProducto(Producto producto) {
        Producto productobd = productoRepository.findByIdProducto(producto.getIdProducto());
        if (productobd != null){
            return productobd;
        }
        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Producto producto) {
        Producto productobd = productoRepository.findByIdProducto(producto.getIdProducto());
        if (productobd == null){
            return null;
        }
        if (!producto.getNombre().isEmpty()) {
            productobd.setNombre(producto.getNombre());
        }
        if (producto.getCantidad() > 0){
            productobd.setCantidad(producto.getCantidad());
        }
        String x = producto.getFechaIngreso().toString();
        if (!producto.getFechaIngreso().toString().startsWith("0")){
            productobd.setFechaIngreso(producto.getFechaIngreso());
        }
        productobd.setUsuarioModifica(producto.getUsuarioModifica());
        productobd.setFechaUsuarioModifica(producto.getFechaUsuarioModifica());
        return productoRepository.save(productobd);
    }

    @Override
    public Producto deleteProducto(Long id) {
        Producto productodb = productoRepository.findByIdProducto(id);
        if (productodb == null){
            return null;
        }
        productoRepository.delete(productodb);
        return productodb;
    }
}

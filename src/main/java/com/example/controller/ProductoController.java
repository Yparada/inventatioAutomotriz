package com.example.controller;

import com.example.dto.Mensaje;
import com.example.dto.ProductoDto;
import com.example.entity.Producto;
import com.example.entity.Usuario;
import com.example.service.ProductoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProducto(@RequestParam(name = "idCliente",required = false) Long idCliente){
        List<Producto> productos = new ArrayList<>();
        if (idCliente == null){
            productos = productoService.getAllProducto();
           if (productos.isEmpty()){
               return ResponseEntity.noContent().build();
           }
        }else{
            productos = productoService.getProductoByUsuario(Usuario.builder().idUsuario(idCliente).build());
            if (productos.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable(name = "id",required = true) Long idProducto){
        Producto producto = productoService.getProductoById(idProducto);
        if (producto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    @GetMapping(value = "/nombre/{nombre}")
    public ResponseEntity<Producto> getProductoByNombre(@PathVariable("nombre") String nombre){
        Producto producto = productoService.getProductoByNombre(nombre);
        if (producto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    @GetMapping(value = "/fecha/{fecha}")
    public ResponseEntity<List<Producto>> getFechaIngreso(@PathVariable("fecha") LocalDate fecha){
        List<Producto> producto = productoService.getFechaIngreso(fecha);
        if (producto.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody ProductoDto productoDto){
        Producto productobd = new Producto();

        productobd = productoService.getProductoByNombre(productoDto.getNombre().toLowerCase());
        if (productobd != null){
            return new ResponseEntity(new Mensaje("El campo nombre del producto ingresado ya esta siendo utilizado"),HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isAllBlank(productoDto.getNombre())){
            return new ResponseEntity(new Mensaje("El campo nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (productoDto.getCantidad() < 1){
           return new ResponseEntity(new Mensaje("El campo cantidad es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (productoDto.getFechaIngreso().isAfter(LocalDate.now())){
            return new ResponseEntity(new Mensaje("El campo fecha no puede ser Superior a la actual"), HttpStatus.BAD_REQUEST);
        }
        if (productoDto.getUsuarioRegistra() == null){
            return new ResponseEntity(new Mensaje("El usuario por el que esta logueado no existe, es necesario para crear productos"),HttpStatus.BAD_REQUEST);
        }

        Producto producto = Producto.builder()
                .nombre(productoDto.getNombre().toLowerCase())
                .cantidad(productoDto.getCantidad())
                .fechaIngreso(productoDto.getFechaIngreso())
                .usuarioRegistra(productoDto.getUsuarioRegistra())
                .fechaUsuarioRegistra(new Date()).build();

        productoService.createProducto(producto);
        return new ResponseEntity(new Mensaje("Producto creado"),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable("id") Long id, @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestBody ProductoDto productoDto){
        Producto productobd = productoService.getProductoById(id);
        //productobd =
        if (productobd == null){
            return new ResponseEntity(new Mensaje("El producto a editar no existe"),HttpStatus.NOT_FOUND);
        }
        productobd = productoService.getProductoByNombre(productoDto.getNombre());
        if (productobd != null){
            return new ResponseEntity(new Mensaje("El campo nombre del producto ingresado ya esta siendo utilizado"),HttpStatus.BAD_REQUEST);
        }
        if (productoDto.getFechaIngreso().isAfter(LocalDate.now())){
            return new ResponseEntity(new Mensaje("El campo fecha no puede ser Superior a la actual"), HttpStatus.BAD_REQUEST);
        }
        if (productoDto.getFechaIngreso() == null){
            return new ResponseEntity(new Mensaje("Fecha vacía -------"),HttpStatus.BAD_REQUEST);

        }
        if (productoDto.getUsuarioModifica() == null){
            return new ResponseEntity(new Mensaje("El usuario por el que esta logueado no existe, es necesario para modificar productos"),HttpStatus.BAD_REQUEST);
        }

        Producto producto = Producto.builder()
                        .idProducto(id)
                        .nombre(productoDto.getNombre().toLowerCase())
                        .cantidad(productoDto.getCantidad())
                        .fechaIngreso(productoDto.getFechaIngreso())
                        .usuarioModifica(productoDto.getUsuarioModifica())
                        .fechaUsuarioModifica(new Date()).build();
        productoService.updateProducto(producto);
        return new ResponseEntity(new Mensaje("Producto actualizado"),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> deleteProducto(@PathVariable("id") Long id){
        Producto producto = productoService.getProductoById(id);
        if (producto == null){
            return new ResponseEntity(new Mensaje("El producto que se ha querido eliminar no existe"),HttpStatus.NOT_FOUND);
        }
        productoService.deleteProducto(producto.getIdProducto());
        return new ResponseEntity(new Mensaje("Producto eliminado"),HttpStatus.OK);
    }



}

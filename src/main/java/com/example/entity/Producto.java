package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    private String nombre;
    private int cantidad;
    private LocalDate fechaIngreso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pk_id_usuario_registra")
   //@Column(name = "pk_id_usuario_registra")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuarioRegistra;
    private Date fechaUsuarioRegistra;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pk_id_usuario_modifica")
    //@Column(name = "pk_id_usuario_modifica")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuarioModifica;
    private Date fechaUsuarioModifica;


}

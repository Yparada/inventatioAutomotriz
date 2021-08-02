package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity()
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    private String nombre;
    private int edad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pk_id_cargo")
    //@Column(name = "pk_id_cargo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cargo cargo;

    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;


}

package com.example.dto;

import com.example.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Date;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductoDto {

    //@NotBlank
    private String nombre;

    @NotNull
    private int cantidad;

    //@Past
    private LocalDate fechaIngreso;

    @NotNull
    private Usuario usuarioRegistra;
    private Date fechaUsuarioRegistra;
    private Usuario usuarioModifica;
    private Date fechaUsuarioModifica;
}

package com.challenge.bankapi.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;


@Data
public class ClienteVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "dni can not null")
    private Integer dni;

    private String nombre;

    private String genero;

    private LocalDate nacimiento;

    private String direccion;

    private String telefono;

    private String contrasena;

    private Integer estado;

}

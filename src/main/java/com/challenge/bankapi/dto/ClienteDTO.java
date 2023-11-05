package com.challenge.bankapi.dto;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer dni;

    private String nombre;

    private String genero;

    private LocalDate nacimiento;

    private String direccion;

    private String telefono;

    private String contrasena;

    private Integer estado;

}

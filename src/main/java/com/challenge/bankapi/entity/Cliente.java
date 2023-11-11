package com.challenge.bankapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dni", unique = true, nullable = false)
    private Integer dni;

    @Column(name = "nombre", length = 80, nullable = false)
    private String nombre;

    @Column(name = "genero", length = 1, nullable = false)
    private String genero;

    @Column(name = "nacimiento", nullable = false)
    private LocalDate nacimiento;

    @Column(name = "direccion", length = 200, nullable = false)
    private String direccion;

    @Column(name = "telefono", unique = true, length = 80)
    private String telefono;

    @Column(name = "contrasena", length = 80, nullable = false)
    private String contrasena;

    @Column(name = "estado", length = 1, nullable = false)
    private Integer estado;

}

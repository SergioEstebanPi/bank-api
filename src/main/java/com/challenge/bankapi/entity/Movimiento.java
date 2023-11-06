package com.challenge.bankapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "movimiento")
public class Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_cuenta", nullable = false)
    private Integer idCuenta;

    @Column(name = "fecha", updatable = false, nullable = false)
    private LocalDateTime fecha;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "saldo", nullable = false)
    private BigDecimal saldo;

}

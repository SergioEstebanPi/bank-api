package com.challenge.bankapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "cuenta")
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @Column(name = "numero", length = 80, nullable = false)
    private String numero;

    @Column(name = "tipo", length = 80, nullable = false)
    private String tipo;

    @Column(name = "saldoInicial", nullable = false, precision = 5, scale = 2)
    private BigDecimal saldoInicial;

    @Column(name = "saldo", nullable = false, precision = 5, scale = 2)
    private BigDecimal saldo;

    @Column(name = "estado", length = 1, nullable = false)
    private Integer estado;

}

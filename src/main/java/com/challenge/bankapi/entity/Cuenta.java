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
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @Column(name = "numero")
    private String numero;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "saldo_inicial", nullable = false)
    private BigDecimal saldoInicial;

    @Column(name = "saldo", nullable = false)
    private BigDecimal saldo;

    @Column(name = "estado")
    private Integer estado;

}

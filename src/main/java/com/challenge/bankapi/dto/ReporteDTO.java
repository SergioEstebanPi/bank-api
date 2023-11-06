package com.challenge.bankapi.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReporteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDateTime fechaMovimiento;

    private Integer dni;

    private String nombreCliente;

    private Integer idCuenta;

    private String numeroCuenta;

    private String tipoCuenta;

    private BigDecimal saldoInicial;

    private Integer estado;

    private BigDecimal valorMovimiento;

    private BigDecimal saldoMovimiento;

}

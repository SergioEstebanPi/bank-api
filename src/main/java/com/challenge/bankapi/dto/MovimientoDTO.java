package com.challenge.bankapi.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MovimientoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer numeroCuenta;

    private LocalDateTime fecha;

    private String tipo;

    private BigDecimal valor;

    private BigDecimal saldo;

}

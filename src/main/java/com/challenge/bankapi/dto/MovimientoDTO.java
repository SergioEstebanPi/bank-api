package com.challenge.bankapi.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MovimientoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer idCuenta;

    private LocalDate fecha;

    private String tipo;

    private BigDecimal valor;

    private BigDecimal saldo;

}

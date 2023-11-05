package com.challenge.bankapi.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class MovimientoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    @NotNull(message = "idCuenta can not null")
    private Integer idCuenta;

    private LocalDate fecha;

    private String tipo;

    @NotNull(message = "valor can not null")
    private BigDecimal valor;

    @NotNull(message = "saldo can not null")
    private BigDecimal saldo;

}

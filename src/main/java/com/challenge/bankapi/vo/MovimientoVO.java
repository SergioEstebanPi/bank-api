package com.challenge.bankapi.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class MovimientoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "idCuenta can not null")
    private Integer idCuenta;

    @NotNull(message = "valor can not null")
    private BigDecimal valor;

}

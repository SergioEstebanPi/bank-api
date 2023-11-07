package com.challenge.bankapi.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class CuentaVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "idCliente can not null")
    private Integer idCliente;

    private String numero;

    private String tipo;

    @NotNull(message = "saldoInicial can not null")
    private BigDecimal saldoInicial;

    private Integer estado;

}

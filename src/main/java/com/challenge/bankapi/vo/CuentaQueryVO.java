package com.challenge.bankapi.vo;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CuentaQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer idCliente;

    private String numero;

    private String tipo;

    private BigDecimal saldoInicial;

    private Integer estado;

}

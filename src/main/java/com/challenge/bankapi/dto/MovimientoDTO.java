package com.challenge.bankapi.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MovimientoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer idCuenta;

    private String numeroCuenta;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fecha;

    private String tipo;

    private BigDecimal valor;

    private BigDecimal saldo;

}

package com.challenge.bankapi.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class ReporteQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer dni;

    private String fechaInicial;

    private String fechaFinal;

}

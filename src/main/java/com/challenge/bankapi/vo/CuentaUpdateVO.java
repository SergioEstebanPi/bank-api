package com.challenge.bankapi.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CuentaUpdateVO extends CuentaVO implements Serializable {
    private static final long serialVersionUID = 1L;

}

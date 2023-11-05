package com.challenge.bankapi.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClienteUpdateVO extends ClienteVO implements Serializable {
    private static final long serialVersionUID = 1L;

}

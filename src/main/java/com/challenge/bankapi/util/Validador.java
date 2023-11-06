package com.challenge.bankapi.util;

import java.math.BigDecimal;

public class Validador {

    private static final BigDecimal LIMITE_DIARIO_RETIRO = new BigDecimal(1000);

    public static boolean excepcionSaldoMinimo(BigDecimal valor, BigDecimal saldo){
        // saldo + valor <  0 -> excepcion
        return saldo.add(valor).compareTo(BigDecimal.ZERO) < 0;
    }

    public static boolean excepcionLimiteRetiroDiario(BigDecimal valor, BigDecimal totalDia){
        // valor < 0 && totalDia + valor > LIMITE_DIARIO_RETIRO -> excepcion
        return valor.compareTo(BigDecimal.ZERO) < 0 // debito
                && totalDia.add(valor.abs()).compareTo(LIMITE_DIARIO_RETIRO) > 0;
    }
}

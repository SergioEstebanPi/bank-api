package com.challenge.bankapi.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void saldoMinimoOk() {
        BigDecimal valor = new BigDecimal(-100);
        BigDecimal saldo = new BigDecimal(500);

        System.out.println(valor);
        System.out.println(saldo);

        assertFalse(Validador.excepcionSaldoMinimo(valor, saldo));

        LocalDateTime now = LocalDateTime.now();
        System.out.print("fecha actual " + now);
        System.out.print("fecha actual " + now);
    }

    @Test
    void saldoMinimoNoOk() {
        BigDecimal valor = new BigDecimal(-1000);
        BigDecimal saldo = new BigDecimal(700);

        System.out.println(valor);
        System.out.println(saldo);

        assertTrue(Validador.excepcionSaldoMinimo(valor, saldo));
    }

    @Test
    void limiteRetiroDiarioOk() {
        BigDecimal valor = new BigDecimal(-999);
        BigDecimal totalDia = new BigDecimal(0);

        System.out.println(valor);
        System.out.println(totalDia);

        assertFalse(Validador.excepcionLimiteRetiroDiario(valor, totalDia));
    }

    @Test
    void limiteRetiroDiarioNoOk() {
        BigDecimal valor = new BigDecimal(-500);
        BigDecimal totalDia = new BigDecimal(900);

        System.out.println(valor);
        System.out.println(totalDia);

        assertTrue(Validador.excepcionLimiteRetiroDiario(valor, totalDia));
    }
}
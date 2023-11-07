package com.challenge.bankapi.service;

import com.challenge.bankapi.entity.Movimiento;
import com.challenge.bankapi.exception.CupoDiarioExcedidoException;
import com.challenge.bankapi.exception.SaldoNoDisponibleException;
import com.challenge.bankapi.repository.MovimientoRepository;
import com.challenge.bankapi.vo.MovimientoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovimientoServiceTest {

    @Mock
    MovimientoRepository movimientoRepository;

    @InjectMocks
    MovimientoService movimientoService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void getById() {
    }

    @Test
    void query() {
    }

    @Test
    void validate() throws SaldoNoDisponibleException, CupoDiarioExcedidoException {
        MovimientoVO v1 = new MovimientoVO();
        Movimiento movimiento = new Movimiento();
        BeanUtils.copyProperties(v1, movimiento);
        movimiento.setValor(BigDecimal.valueOf(-100));

        // debito
        BigDecimal result = movimiento.getSaldo().subtract(v1.getValor());

        // credito
        BigDecimal result2 = movimiento.getSaldo().add(v1.getValor());

        System.out.println(result);
        System.out.println(result2);

        //assertDoesNotThrow(SaldoNoDisponibleException.class);
        //when(movimientoService.validate(v1)).thenThrow(new SaldoNoDisponibleException());
    }
}
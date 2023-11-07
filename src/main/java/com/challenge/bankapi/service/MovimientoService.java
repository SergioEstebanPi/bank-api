package com.challenge.bankapi.service;

import com.challenge.bankapi.entity.Cuenta;
import com.challenge.bankapi.entity.Movimiento;
import com.challenge.bankapi.dto.MovimientoDTO;
import com.challenge.bankapi.exception.CupoDiarioExcedidoException;
import com.challenge.bankapi.exception.SaldoNoDisponibleException;
import com.challenge.bankapi.repository.CuentaRepository;
import com.challenge.bankapi.repository.MovimientoRepository;
import com.challenge.bankapi.util.Validador;
import com.challenge.bankapi.vo.MovimientoQueryVO;
import com.challenge.bankapi.vo.MovimientoUpdateVO;
import com.challenge.bankapi.vo.MovimientoVO;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Transactional
    public Integer save(MovimientoVO vO) throws SaldoNoDisponibleException, CupoDiarioExcedidoException {
        Cuenta cuenta = cuentaRepository.findById(vO.getIdCuenta())
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + vO.getIdCuenta()));

        BigDecimal valor = vO.getValor();
        validateSaldoMinimo(valor, cuenta.getSaldo());
        validateCupoDiarioExcedido(valor, cuenta.getId());

        String tipo = (valor.compareTo(BigDecimal.ZERO) < 0 ? "Retiro de " : "Deposito de ") + valor;
        BigDecimal nuevoSaldo = cuenta.getSaldo().add(valor);

        Movimiento bean = new Movimiento();
        BeanUtils.copyProperties(vO, bean);
        bean.setFecha(LocalDateTime.now());
        bean.setTipo(tipo);
        bean.setSaldo(nuevoSaldo);
        bean = movimientoRepository.save(bean);

        cuenta.setSaldo(bean.getSaldo());
        cuentaRepository.save(cuenta);
        return bean.getId();
    }

    public void delete(Integer id) {
        movimientoRepository.deleteById(id);
    }

    public void update(Integer id, MovimientoUpdateVO vO) {
        Movimiento bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        movimientoRepository.save(bean);
    }

    public MovimientoDTO getById(Integer id) {
        Movimiento original = requireOne(id);
        return toDTO(original);
    }

    public Page<MovimientoDTO> query(MovimientoQueryVO vO) {
        return new PageImpl(movimientoRepository.findAll().stream()
                .map(movimiento -> {
                    String numeroCuenta = cuentaRepository.findById(movimiento.getIdCuenta())
                            .get()
                            .getNumero();
                    MovimientoDTO movimientoDTO = toDTO(movimiento);
                    movimientoDTO.setNumeroCuenta(numeroCuenta);
                    return movimientoDTO;
                }).collect(Collectors.toList()));
    }

    private MovimientoDTO toDTO(Movimiento original) {
        MovimientoDTO bean = new MovimientoDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Movimiento requireOne(Integer id) {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public void validateSaldoMinimo(BigDecimal valor, BigDecimal saldo) throws SaldoNoDisponibleException {
        if(Validador.excepcionSaldoMinimo(valor, saldo)) {
            throw new SaldoNoDisponibleException("Saldo no disponible");
        }
    }

    public void validateCupoDiarioExcedido(BigDecimal valor, Integer idCuenta) throws CupoDiarioExcedidoException {
        BigDecimal totalDia = movimientoRepository.findByIdCuentaAndFechaBetween(idCuenta,
                        LocalDate.now().atStartOfDay(), LocalDateTime.now())
                .stream()
                .filter(movimiento -> movimiento.getValor().compareTo(BigDecimal.ZERO) < 0)
                .map(movimiento -> movimiento.getValor())
                .reduce(BigDecimal.ZERO, (a, b) -> a.abs().add(b.abs()));
        if(Validador.excepcionLimiteRetiroDiario(valor, totalDia)) {
            throw new CupoDiarioExcedidoException("Cupo diario Excedido");
        }
    }
}

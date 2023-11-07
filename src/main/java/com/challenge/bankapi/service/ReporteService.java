package com.challenge.bankapi.service;

import com.challenge.bankapi.dto.ReporteDTO;
import com.challenge.bankapi.entity.Cliente;
import com.challenge.bankapi.entity.Cuenta;
import com.challenge.bankapi.entity.Movimiento;
import com.challenge.bankapi.repository.ClienteRepository;
import com.challenge.bankapi.repository.CuentaRepository;
import com.challenge.bankapi.repository.MovimientoRepository;
import com.challenge.bankapi.vo.ReporteQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Page<ReporteDTO> query(ReporteQueryVO vO, Pageable pageable) throws Exception {
        Integer dni = vO.getDni();
        Cliente cliente = clienteRepository.findByDni(dni)
            .orElseThrow(() -> new NoSuchElementException("Resource not found: " + dni));

        List<Cuenta> cuentas = cuentaRepository.findByIdCliente(cliente.getId());
        Set<Integer> cuentasIds = cuentas.stream()
                .map(cuenta -> cuenta.getId())
                .collect(Collectors.toSet());

        try {
            LocalDateTime fechaInicial = LocalDate.parse(vO.getFechaInicial(), DATE_FORMATTER).atTime(LocalTime.MIN);
            LocalDateTime fechaFinal = LocalDate.parse(vO.getFechaFinal(), DATE_FORMATTER).atTime(LocalTime.MAX);

            return new PageImpl<>(movimientoRepository.findByIdCuentaInAndFechaBetween(cuentasIds,
                            fechaInicial, fechaFinal, pageable).stream()
                    .map(movimiento -> toReporteDTO(cliente,
                            cuentas.stream()
                                    .filter(cuenta -> cuenta.getId().equals(movimiento.getIdCuenta()))
                                    .findFirst().get(),
                            movimiento))
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public byte[] queryBase64(ReporteQueryVO vO, Pageable pageable) throws Exception {
        /*
        ReporteDTO reporteDTO = new ReporteDTO();
        reporteDTO.setDni(1234);
        reporteDTO.setEstado(1);
        reporteDTO.setSaldoInicial(BigDecimal.TEN);

        List<ReporteDTO> datosReporte = new ArrayList<>();
        datosReporte.add(reporteDTO);
         */

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new ObjectOutputStream(baos).writeObject(query(vO, pageable).getContent());

        return Base64.getEncoder().encode(baos.toByteArray());
    }

    private ReporteDTO toReporteDTO(Cliente cliente, Cuenta cuenta, Movimiento movimiento) {
        ReporteDTO bean = new ReporteDTO();
        bean.setDni(cliente.getDni());
        bean.setNombreCliente(cliente.getNombre());
        bean.setIdCuenta(cuenta.getId());
        bean.setNumeroCuenta(cuenta.getNumero());
        bean.setTipoCuenta(cuenta.getTipo());
        bean.setSaldoInicial(cuenta.getSaldoInicial());
        bean.setEstado(cuenta.getEstado());
        bean.setFechaMovimiento(movimiento.getFecha());
        bean.setValorMovimiento(movimiento.getValor());
        bean.setSaldoMovimiento(movimiento.getSaldo());
        return bean;
    }
}

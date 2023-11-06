package com.challenge.bankapi.repository;

import com.challenge.bankapi.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer>, JpaSpecificationExecutor<Movimiento> {

    List<Movimiento> findByIdCuentaAndFechaBetween(Integer idCuenta, LocalDateTime startDate, LocalDateTime endDate);
}
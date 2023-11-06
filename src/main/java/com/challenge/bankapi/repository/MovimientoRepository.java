package com.challenge.bankapi.repository;

import com.challenge.bankapi.entity.Movimiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer>, JpaSpecificationExecutor<Movimiento> {

    List<Movimiento> findByIdCuentaAndFechaBetween(Integer idCuenta, LocalDateTime startDate, LocalDateTime endDate);
    Page<Movimiento> findByIdCuentaInAndFechaBetween(Set<Integer> cuentasIds, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
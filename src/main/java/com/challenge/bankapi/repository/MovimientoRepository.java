package com.challenge.bankapi.repository;

import com.challenge.bankapi.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer>, JpaSpecificationExecutor<Movimiento> {

}
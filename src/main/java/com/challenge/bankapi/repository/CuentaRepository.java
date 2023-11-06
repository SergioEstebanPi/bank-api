package com.challenge.bankapi.repository;

import com.challenge.bankapi.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer>, JpaSpecificationExecutor<Cuenta> {

    List<Cuenta> findByIdCliente(Integer idCliente);
}
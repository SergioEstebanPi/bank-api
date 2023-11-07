package com.challenge.bankapi.service;

import com.challenge.bankapi.entity.Cuenta;
import com.challenge.bankapi.dto.CuentaDTO;
import com.challenge.bankapi.repository.ClienteRepository;
import com.challenge.bankapi.repository.CuentaRepository;
import com.challenge.bankapi.vo.CuentaQueryVO;
import com.challenge.bankapi.vo.CuentaUpdateVO;
import com.challenge.bankapi.vo.CuentaVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Integer save(CuentaVO vO) {
        clienteRepository.findById(vO.getIdCliente())
                .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado: " + vO.getIdCliente()));
        Cuenta bean = new Cuenta();
        BeanUtils.copyProperties(vO, bean);
        bean.setEstado(1);
        bean.setSaldo(vO.getSaldoInicial());
        bean = cuentaRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        cuentaRepository.deleteById(id);
    }

    public void update(Integer id, CuentaUpdateVO vO) {
        Cuenta bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        cuentaRepository.save(bean);
    }

    public CuentaDTO getById(Integer id) {
        Cuenta original = requireOne(id);
        return toDTO(original);
    }

    public Page<CuentaDTO> query(CuentaQueryVO vO) {
        return new PageImpl(cuentaRepository.findAll());
    }

    private CuentaDTO toDTO(Cuenta original) {
        CuentaDTO bean = new CuentaDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Cuenta requireOne(Integer id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cuenta no encontrada: " + id));
    }
}

package com.challenge.bankapi.service;

import com.challenge.bankapi.entity.Cuenta;
import com.challenge.bankapi.dto.CuentaDTO;
import com.challenge.bankapi.repository.CuentaRepository;
import com.challenge.bankapi.vo.CuentaQueryVO;
import com.challenge.bankapi.vo.CuentaUpdateVO;
import com.challenge.bankapi.vo.CuentaVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public Integer save(CuentaVO vO) {
        Cuenta bean = new Cuenta();
        BeanUtils.copyProperties(vO, bean);
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
        throw new UnsupportedOperationException();
    }

    private CuentaDTO toDTO(Cuenta original) {
        CuentaDTO bean = new CuentaDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Cuenta requireOne(Integer id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}

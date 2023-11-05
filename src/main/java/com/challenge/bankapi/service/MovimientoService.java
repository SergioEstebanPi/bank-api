package com.challenge.bankapi.service;

import com.challenge.bankapi.entity.Movimiento;
import com.challenge.bankapi.dto.MovimientoDTO;
import com.challenge.bankapi.repository.MovimientoRepository;
import com.challenge.bankapi.vo.MovimientoQueryVO;
import com.challenge.bankapi.vo.MovimientoUpdateVO;
import com.challenge.bankapi.vo.MovimientoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    public Integer save(MovimientoVO vO) {
        Movimiento bean = new Movimiento();
        BeanUtils.copyProperties(vO, bean);
        bean = movimientoRepository.save(bean);
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
        throw new UnsupportedOperationException();
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
}

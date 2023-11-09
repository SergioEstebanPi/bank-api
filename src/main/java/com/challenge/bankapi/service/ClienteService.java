package com.challenge.bankapi.service;

import com.challenge.bankapi.entity.Cliente;
import com.challenge.bankapi.dto.ClienteDTO;
import com.challenge.bankapi.repository.ClienteRepository;
import com.challenge.bankapi.vo.ClienteQueryVO;
import com.challenge.bankapi.vo.ClienteUpdateVO;
import com.challenge.bankapi.vo.ClienteVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Integer save(ClienteVO vO) {
        Cliente bean = new Cliente();
        BeanUtils.copyProperties(vO, bean);
        bean.setEstado(1);
        bean = clienteRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        clienteRepository.deleteById(id);
    }

    public void update(Integer id, ClienteUpdateVO vO) {
        Cliente bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        clienteRepository.save(bean);
    }

    public ClienteDTO getById(Integer id) {
        Cliente original = requireOne(id);
        return toDTO(original);
    }

    public Page<ClienteDTO> query(ClienteQueryVO vO) {
        return new PageImpl(clienteRepository.findAll());
    }

    private ClienteDTO toDTO(Cliente original) {
        ClienteDTO bean = new ClienteDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Cliente requireOne(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado: : " + id));
    }
}

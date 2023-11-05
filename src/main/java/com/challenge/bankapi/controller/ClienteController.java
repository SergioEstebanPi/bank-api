package com.challenge.bankapi.controller;

import com.challenge.bankapi.dto.ClienteDTO;
import com.challenge.bankapi.service.ClienteService;
import com.challenge.bankapi.vo.ClienteQueryVO;
import com.challenge.bankapi.vo.ClienteUpdateVO;
import com.challenge.bankapi.vo.ClienteVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public String save(@Valid @RequestBody ClienteVO vO) {
        return clienteService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        clienteService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody ClienteUpdateVO vO) {
        clienteService.update(id, vO);
    }

    @GetMapping("/{id}")
    public ClienteDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return clienteService.getById(id);
    }

    @GetMapping
    public Page<ClienteDTO> query(@Valid ClienteQueryVO vO) {
        return clienteService.query(vO);
    }
}

package com.challenge.bankapi.controller;

import com.challenge.bankapi.dto.CuentaDTO;
import com.challenge.bankapi.service.CuentaService;
import com.challenge.bankapi.vo.CuentaQueryVO;
import com.challenge.bankapi.vo.CuentaUpdateVO;
import com.challenge.bankapi.vo.CuentaVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    public String save(@Valid @RequestBody CuentaVO vO) {
        return cuentaService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        cuentaService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody CuentaUpdateVO vO) {
        cuentaService.update(id, vO);
    }

    @GetMapping("/{id}")
    public CuentaDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return cuentaService.getById(id);
    }

    @GetMapping
    public Page<CuentaDTO> query(@Valid CuentaQueryVO vO) {
        return cuentaService.query(vO);
    }
}

package com.challenge.bankapi.controller;

import com.challenge.bankapi.dto.MovimientoDTO;
import com.challenge.bankapi.exception.CupoDiarioExcedidoException;
import com.challenge.bankapi.exception.SaldoNoDisponibleException;
import com.challenge.bankapi.service.MovimientoService;
import com.challenge.bankapi.vo.MovimientoQueryVO;
import com.challenge.bankapi.vo.MovimientoUpdateVO;
import com.challenge.bankapi.vo.MovimientoVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/movimiento")
@CrossOrigin(origins = "*")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    public String save(@Valid @RequestBody MovimientoVO vO) throws SaldoNoDisponibleException, CupoDiarioExcedidoException {
        return movimientoService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        movimientoService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody MovimientoUpdateVO vO) {
        movimientoService.update(id, vO);
    }

    @GetMapping("/{id}")
    public MovimientoDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return movimientoService.getById(id);
    }

    @GetMapping
    public Page<MovimientoDTO> query(@Valid MovimientoQueryVO vO) {
        return movimientoService.query(vO);
    }
}

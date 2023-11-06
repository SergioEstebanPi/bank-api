package com.challenge.bankapi.controller;

import com.challenge.bankapi.dto.ReporteDTO;
import com.challenge.bankapi.service.ReporteService;
import com.challenge.bankapi.vo.ReporteQueryVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/reporte")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public Page<ReporteDTO> query(@Valid ReporteQueryVO vO,
                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.by("idCuenta").ascending()
                        .and(Sort.by("fecha").descending()));
        return reporteService.query(vO, pageable);
    }
}

package com.challenge.bankapi.controller;

import com.challenge.bankapi.dto.PDFBase64;
import com.challenge.bankapi.dto.ReporteDTO;
import com.challenge.bankapi.service.ReporteService;
import com.challenge.bankapi.util.PDFGenerator;
import com.challenge.bankapi.vo.ReporteQueryVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/reporte")
@CrossOrigin(origins = "*")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public Page<ReporteDTO> query(@Valid ReporteQueryVO vO,
                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size,
                Sort.by("idCuenta").ascending()
                        .and(Sort.by("fecha").descending()));
        return reporteService.query(vO, pageable);
    }

    @GetMapping(value = "/base64PDF")
    public PDFBase64 queryBase64(@Valid ReporteQueryVO vO,
                                 @RequestParam(value = "page", defaultValue = "0") int page,
                                 @RequestParam(value = "size", defaultValue = "10") int size)
            throws Exception {
        Pageable pageable = PageRequest.of(page, size,
                Sort.by("idCuenta").ascending()
                        .and(Sort.by("fecha").descending()));
        PDFBase64 pdfBase64 = new PDFBase64();
        PDFGenerator generator = new PDFGenerator();
        List<ReporteDTO> reporteDTOList = reporteService.query(vO, pageable).toList();
        pdfBase64.setBase64Str(generator.generate(reporteDTOList));
        return pdfBase64;
    }
}

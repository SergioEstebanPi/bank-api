package com.challenge.bankapi.controller;

import com.challenge.bankapi.dto.CuentaDTO;
import com.challenge.bankapi.service.CuentaService;
import com.challenge.bankapi.vo.CuentaQueryVO;
import com.challenge.bankapi.vo.CuentaUpdateVO;
import com.challenge.bankapi.vo.CuentaVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@AutoConfigureMockMvc
class CuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CuentaService cuentaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void save() throws Exception {
        CuentaVO cuentaVO = new CuentaVO();
        cuentaVO.setIdCliente(1);
        cuentaVO.setNumero("1234");
        cuentaVO.setSaldoInicial(BigDecimal.TEN);

        when(cuentaService.save(cuentaVO)).thenReturn(1);

        this.mockMvc.perform(post("/cuenta")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(new MediaType("application", "json",
                                Charset.forName("UTF-8")))
                        .content(objectMapper.writeValueAsString(cuentaVO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNumber());
    }

    @Test
    void deleteTest() throws Exception {
        CuentaVO cuentaVO = new CuentaVO();
        cuentaVO.setIdCliente(1);

        this.mockMvc.perform(
                        delete(URI.create("/cuenta/1"))
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateOk() {
        CuentaUpdateVO cuentaUpdateVO = new CuentaUpdateVO();
        cuentaUpdateVO.setNumero("1234");

        Integer idUpdate = 1;
        cuentaService.update(idUpdate, cuentaUpdateVO);

        verify(cuentaService, times(1)).update(idUpdate, cuentaUpdateVO);
    }

    @Test
    void updateNoOk() {
        CuentaUpdateVO cuentaUpdateVO = new CuentaUpdateVO();
        cuentaUpdateVO.setNumero("1234");

        doThrow(new NoSuchElementException()).when(cuentaService)
                .update(any(Integer.class),any(CuentaUpdateVO.class));
        cuentaService.update(0, null);
    }

    @Test
    void getById() throws Exception {
        CuentaDTO cuentaDTO = new CuentaDTO();

        Integer idCuenta = 1;
        when(cuentaService.getById(idCuenta)).thenReturn(cuentaDTO);

        this.mockMvc.perform(
                        get("/cuenta/" + idCuenta)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void query() throws Exception {
        CuentaQueryVO cuentaQueryVO = new CuentaQueryVO();

        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setId(1);
        List<CuentaDTO> lista = Arrays.asList(cuentaDTO);
        when(cuentaService.query(cuentaQueryVO)).thenReturn(new PageImpl(lista));

        this.mockMvc.perform(
                        get("/cuenta")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content")
                        .exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id")
                        .isNotEmpty());
    }
}
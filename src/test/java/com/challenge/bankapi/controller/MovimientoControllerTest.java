package com.challenge.bankapi.controller;

import com.challenge.bankapi.dto.MovimientoDTO;
import com.challenge.bankapi.service.MovimientoService;
import com.challenge.bankapi.vo.MovimientoQueryVO;
import com.challenge.bankapi.vo.MovimientoUpdateVO;
import com.challenge.bankapi.vo.MovimientoVO;
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
class MovimientoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovimientoService movimientoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void save() throws Exception {
        MovimientoVO movimientoVO = new MovimientoVO();
        movimientoVO.setIdCuenta(1);
        movimientoVO.setValor(BigDecimal.TEN);

        when(movimientoService.save(movimientoVO)).thenReturn(1);

        this.mockMvc.perform(post("/movimiento")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(new MediaType("application", "json",
                                Charset.forName("UTF-8")))
                        .content(objectMapper.writeValueAsString(movimientoVO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNumber());
    }

    @Test
    void deleteTest() throws Exception {
        this.mockMvc.perform(
                        delete(URI.create("/movimiento/1"))
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateOk() {
        MovimientoUpdateVO movimientoUpdateVO = new MovimientoUpdateVO();
        movimientoUpdateVO.setValor(BigDecimal.TEN);

        Integer idUpdate = 1;
        movimientoService.update(idUpdate, movimientoUpdateVO);

        verify(movimientoService, times(1)).update(idUpdate, movimientoUpdateVO);
    }

    @Test
    void updateNoOk() {
        MovimientoUpdateVO movimientoUpdateVO = new MovimientoUpdateVO();
        movimientoUpdateVO.setValor(BigDecimal.TEN);

        doThrow(new NoSuchElementException()).when(movimientoService)
                .update(any(Integer.class),any(MovimientoUpdateVO.class));
        movimientoService.update(0, null);
    }

    @Test
    void getById() throws Exception {
        MovimientoDTO movimientoDTO = new MovimientoDTO();

        Integer idMovimiento = 1;
        when(movimientoService.getById(idMovimiento)).thenReturn(movimientoDTO);

        this.mockMvc.perform(
                        get("/movimiento/" + idMovimiento)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void query() throws Exception {
        MovimientoQueryVO movimientoQueryVO = new MovimientoQueryVO();

        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setId(1);
        List<MovimientoDTO> lista = Arrays.asList(movimientoDTO);
        when(movimientoService.query(movimientoQueryVO)).thenReturn(new PageImpl(lista));

        this.mockMvc.perform(
                        get("/movimiento")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content")
                        .exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id")
                        .isNotEmpty());
    }
}
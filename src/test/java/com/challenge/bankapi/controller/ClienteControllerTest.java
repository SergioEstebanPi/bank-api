package com.challenge.bankapi.controller;

import com.challenge.bankapi.dto.ClienteDTO;
import com.challenge.bankapi.entity.Cliente;
import com.challenge.bankapi.repository.ClienteRepository;
import com.challenge.bankapi.service.ClienteService;
import com.challenge.bankapi.vo.ClienteQueryVO;
import com.challenge.bankapi.vo.ClienteUpdateVO;
import com.challenge.bankapi.vo.ClienteVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void save() throws Exception {
        ClienteVO clienteVO = new ClienteVO();
        clienteVO.setDni(1234);
        clienteVO.setNombre("cliente");

        when(clienteService.save(clienteVO)).thenReturn(1);

        this.mockMvc.perform(post("/cliente")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(new MediaType("application", "json",
                                Charset.forName("UTF-8")))
                        .content(objectMapper.writeValueAsString(clienteVO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNumber());
    }

    @Test
    void deleteTest() throws Exception {
        this.mockMvc.perform(
                        delete(URI.create("/cliente/1"))
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateOk() {
        ClienteUpdateVO clienteUpdateVO = new ClienteUpdateVO();
        clienteUpdateVO.setNombre("Cliente nuevo");

        Integer idUpdate = 1;
        clienteService.update(idUpdate, clienteUpdateVO);

        verify(clienteService, times(1)).update(idUpdate, clienteUpdateVO);
    }

    @Test
    void updateNoOk() {
        ClienteUpdateVO clienteUpdateVO = new ClienteUpdateVO();
        clienteUpdateVO.setNombre("Cliente nuevo");

        doThrow(new NoSuchElementException()).when(clienteService)
                .update(any(Integer.class),any(ClienteUpdateVO.class));
        clienteService.update(0, null);
    }

    @Test
    void getById() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO();

        Integer idCliente = 1;
        when(clienteService.getById(idCliente)).thenReturn(clienteDTO);

        this.mockMvc.perform(
                        get("/cliente/" + idCliente)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void query() throws Exception {
        ClienteQueryVO clienteQueryVO = new ClienteQueryVO();

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(1);
        List<ClienteDTO> lista = Arrays.asList(clienteDTO);
        when(clienteService.query(clienteQueryVO)).thenReturn(new PageImpl(lista));

        this.mockMvc.perform(
                get("/cliente")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content")
                        .exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id")
                        .isNotEmpty());
    }
}
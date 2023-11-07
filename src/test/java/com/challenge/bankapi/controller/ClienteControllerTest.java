package com.challenge.bankapi.controller;

import com.challenge.bankapi.dto.ClienteDTO;
import com.challenge.bankapi.service.ClienteService;
import com.challenge.bankapi.vo.ClienteQueryVO;
import com.challenge.bankapi.vo.ClienteUpdateVO;
import com.challenge.bankapi.vo.ClienteVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    void save() throws Exception {
        ClienteVO clienteVO = new ClienteVO();

        when(clienteService.save(clienteVO)).thenReturn(1);

        this.mockMvc.perform(
                        post("/cliente", clienteVO)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[*].id")
                        .isNotEmpty());
    }

    @Test
    void deleteTest() throws Exception {
        ClienteVO clienteVO = new ClienteVO();

        when(clienteService.save(clienteVO)).thenReturn(1);

        this.mockMvc.perform(
                        delete(URI.create("/cliente/1"))
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        ClienteUpdateVO clienteUpdateVO = new ClienteUpdateVO();

        clienteService.update(1, clienteUpdateVO);

        this.mockMvc.perform(
                        put("/cliente/1", clienteUpdateVO)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO();

        when(clienteService.getById(1)).thenReturn(clienteDTO);

        this.mockMvc.perform(
                        get("/cliente/1")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void query() throws Exception {
        ClienteQueryVO clienteQueryVO = new ClienteQueryVO();

        when(clienteService.query(clienteQueryVO)).thenReturn(Page.empty());

        this.mockMvc.perform(
                get("/cliente")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content")
                        .exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[*].id")
                        .isNotEmpty());
    }
}
package com.example.demo.controllers;


import com.example.demo.dtos.Automovel;
import com.example.demo.services.AutomovelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AutomovelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AutomovelService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void criarAutomovel_MustReturnStatus200() throws Exception {

        LocalDate date = LocalDate.now();
        Automovel automovel = Automovel.builder()
                .dataCadastro(date)
                .marca("Toyota")
                .valor(1000000D)
                .modelo("Hylux")
                .build();

        Automovel response = Automovel.builder()
                .id(1)
                .dataCadastro(date)
                .marca("Toyota")
                .valor(1000000D)
                .modelo("Hylux")
                .build();

        when(service.cadastraAutomovel(any(Automovel.class)))
                .thenReturn(response);

        mockMvc.perform(post("/automoveis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(automovel)))
                .andExpect(status().isCreated());
    }

    @Test
    void getAll_MustReturnStatus200() throws Exception {
        LocalDate date = LocalDate.now();

        Automovel response = Automovel.builder()
                .id(1)
                .dataCadastro(date)
                .marca("Toyota")
                .valor(1000000D)
                .modelo("Hylux")
                .build();
        ArrayList<Automovel> responseList = new ArrayList<>();
        responseList.add(response);
        when(service.getAll())
                .thenReturn(responseList);

        mockMvc.perform(get("/automoveis")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].modelo").value("Hylux"));
    }
}

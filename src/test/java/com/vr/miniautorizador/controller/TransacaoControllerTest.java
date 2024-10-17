package com.vr.miniautorizador.controller;

import com.vr.miniautorizador.dto.TransacaoDTO;
import com.vr.miniautorizador.service.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TransacaoControllerTest {

    @Mock
    private TransacaoService transacaoService;

    @InjectMocks
    private TransacaoController transacaoController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).build();
    }

    @Test
    public void testRealizarTransacao() throws Exception {
        TransacaoDTO transacaoDTO = new TransacaoDTO("1234567890","1234", new BigDecimal(100));

        mockMvc.perform(post("/transacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numeroCartao\":\"1234567890\",\"valor\":100}"))
                .andExpect(status().isCreated());
    }
}
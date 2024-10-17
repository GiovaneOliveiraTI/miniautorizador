package com.vr.miniautorizador.controller;

import com.vr.miniautorizador.dto.CartaoDTO;
import com.vr.miniautorizador.service.CartaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

public class CartaoControllerTest {

    @Mock
    private CartaoService cartaoService;

    @InjectMocks
    private CartaoController cartaoController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cartaoController).build();
    }

    @Test
    public void testCriarCartao() throws Exception {
        CartaoDTO cartaoDTO = new CartaoDTO("1234567890", "1234");

        mockMvc.perform(post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numeroCartao\":\"1234567890\",\"senha\":\"1234\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testConsultarSaldo() throws Exception {
        String numeroCartao = "1234567890";
        BigDecimal saldo = new BigDecimal(100);

        when(cartaoService.consultarSaldo(numeroCartao)).thenReturn(saldo);

        mockMvc.perform(get("/cartoes/" + numeroCartao)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
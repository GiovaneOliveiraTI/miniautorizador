package com.vr.miniautorizador.service;

import com.vr.miniautorizador.domain.Cartao;
import com.vr.miniautorizador.dto.TransacaoDTO;
import com.vr.miniautorizador.validation.ITransacaoValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TransacaoServiceTest {

    @Mock
    private ITransacaoValidation transacaoValidation;

    @Mock
    private ICartaoQueryService cartaoQueryService;

    @Mock
    private ICartaoService cartaoService;

    @InjectMocks
    private TransacaoService transacaoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRealizarTransacao() {
        TransacaoDTO transacaoDTO = new TransacaoDTO("1234567890", "1234", new BigDecimal(100));
        Cartao cartao = new Cartao("1234567890", "1234");

        when(cartaoQueryService.findByNumeroCartao(transacaoDTO.numeroCartao())).thenReturn(cartao);

        transacaoService.realizarTransacao(transacaoDTO);

        verify(transacaoValidation).validarTransacao(transacaoDTO);
        verify(cartaoService).debitarSaldo(cartao, transacaoDTO.valor());
        verify(cartaoService).salvarCartao(cartao);
    }
}
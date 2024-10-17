package com.vr.miniautorizador.service;

import com.vr.miniautorizador.domain.Cartao;
import com.vr.miniautorizador.exception.CartaoInexistenteException;
import com.vr.miniautorizador.repository.CartaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CartaoQueryServiceTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @InjectMocks
    private CartaoQueryService cartaoQueryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByNumeroCartao() {
        String numeroCartao = "1234567890";
        Cartao cartao = new Cartao();
        cartao.setNumeroCartao(numeroCartao);

        when(cartaoRepository.findByNumeroCartao(numeroCartao)).thenReturn(Optional.of(cartao));

        Cartao result = cartaoQueryService.findByNumeroCartao(numeroCartao);

        assertEquals(cartao, result);
    }

    @Test
    public void testFindByNumeroCartao_NotFound() {
        String numeroCartao = "123456";

        when(cartaoRepository.findByNumeroCartao(numeroCartao)).thenReturn(Optional.empty());

        assertThrows(CartaoInexistenteException.class, () -> cartaoQueryService.findByNumeroCartao(numeroCartao));
    }
}
package com.vr.miniautorizador.service;

import com.vr.miniautorizador.domain.Cartao;
import com.vr.miniautorizador.dto.CartaoDTO;
import com.vr.miniautorizador.exception.CartaoExistenteException;
import com.vr.miniautorizador.exception.CartaoInexistenteException;
import com.vr.miniautorizador.repository.CartaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CartaoServiceTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @InjectMocks
    private CartaoService cartaoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarCartao() {
        CartaoDTO cartaoDTO = new CartaoDTO("1234567890", "1234");

        when(cartaoRepository.findByNumeroCartao(cartaoDTO.numeroCartao())).thenReturn(Optional.empty());

        cartaoService.criarCartao(cartaoDTO);

        verify(cartaoRepository).save(any(Cartao.class));
    }

    @Test
    public void testCriarCartao_CartaoExistente() {
        CartaoDTO cartaoDTO = new CartaoDTO("1234567890", "1234");
        Cartao cartao = new Cartao(cartaoDTO.numeroCartao(), cartaoDTO.senha());

        when(cartaoRepository.findByNumeroCartao(cartaoDTO.numeroCartao())).thenReturn(Optional.of(cartao));

        assertThrows(CartaoExistenteException.class, () -> cartaoService.criarCartao(cartaoDTO));
    }

    @Test
    public void testConsultarSaldo() {
        String numeroCartao = "1234567890";
        Cartao cartao = new Cartao(numeroCartao, "1234");

        when(cartaoRepository.findByNumeroCartao(numeroCartao)).thenReturn(Optional.of(cartao));

        BigDecimal result = cartaoService.consultarSaldo(numeroCartao);

        assertEquals(cartao.getSaldo(), result);
    }

    @Test
    public void testConsultarSaldo_CartaoInexistente() {
        String numeroCartao = "1234567890";

        when(cartaoRepository.findByNumeroCartao(numeroCartao)).thenReturn(Optional.empty());

        assertThrows(CartaoInexistenteException.class, () -> cartaoService.consultarSaldo(numeroCartao));
    }

    @Test
    public void testDebitarSaldo() {
        Cartao cartao = new Cartao("1234567890", "1234");
        BigDecimal valor = new BigDecimal(100);
        BigDecimal saldoInicial = cartao.getSaldo();
        cartaoService.debitarSaldo(cartao, valor);
        assertEquals(saldoInicial.subtract(valor), cartao.getSaldo());
    }

    @Test
    public void testSalvarCartao() {
        Cartao cartao = new Cartao("1234567890", "1234");
        cartaoService.salvarCartao(cartao);
        verify(cartaoRepository).save(any(Cartao.class));
    }
}
package com.vr.miniautorizador.service;

import com.vr.miniautorizador.domain.Cartao;
import com.vr.miniautorizador.dto.CartaoDTO;
import com.vr.miniautorizador.exception.CartaoInexistenteException;
import com.vr.miniautorizador.repository.CartaoRepository;
import com.vr.miniautorizador.validation.CartaoValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CartaoServiceTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @Mock
    private CartaoValidation cartaoValidation;

    @InjectMocks
    private CartaoService cartaoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarCartao() {
        CartaoDTO cartaoDTO = new CartaoDTO("1234567890", "1234");
        Cartao novoCartao = new Cartao(cartaoDTO.numeroCartao(), cartaoDTO.senha());

        doNothing().when(cartaoValidation).verificarCartaoExistente(cartaoDTO.numeroCartao());
        when(cartaoRepository.save(any(Cartao.class))).thenReturn(novoCartao);

        cartaoService.criarCartao(cartaoDTO);

        verify(cartaoValidation, times(1)).verificarCartaoExistente(cartaoDTO.numeroCartao());
        verify(cartaoRepository, times(1)).save(any(Cartao.class));
    }

    @Test
    public void testConsultarSaldo() {
        String numeroCartao = "1234567890";
        BigDecimal saldo = new BigDecimal("1000.00");
        Cartao cartao = new Cartao(numeroCartao, "1234");
        cartao.setSaldo(saldo);

        when(cartaoRepository.findByNumeroCartao(numeroCartao)).thenReturn(Optional.of(cartao));

        BigDecimal result = cartaoService.consultarSaldo(numeroCartao);

        assertEquals(saldo, result);
    }

    @Test
    public void testConsultarSaldo_CartaoInexistente() {
        String numeroCartao = "1234567890";

        when(cartaoRepository.findByNumeroCartao(numeroCartao)).thenReturn(Optional.empty());

        assertThrows(CartaoInexistenteException.class, () -> cartaoService.consultarSaldo(numeroCartao));
    }

    @Test
    public void testDebitarSaldo() {
        String numeroCartao = "1234567890";
        BigDecimal saldo = new BigDecimal("1000.00");
        BigDecimal valorDebito = new BigDecimal("500.00");
        Cartao cartao = new Cartao(numeroCartao, "1234");
        cartao.setSaldo(saldo);

        cartaoService.debitarSaldo(cartao, valorDebito);

        assertEquals(saldo.subtract(valorDebito), cartao.getSaldo());
    }

    @Test
    public void testSalvarCartao() {
        String numeroCartao = "1234567890";
        Cartao cartao = new Cartao(numeroCartao, "1234");

        when(cartaoRepository.save(cartao)).thenReturn(cartao);

        cartaoService.salvarCartao(cartao);

        verify(cartaoRepository, times(1)).save(cartao);
    }
}
package com.vr.miniautorizador.validator;

import com.vr.miniautorizador.domain.Cartao;
import com.vr.miniautorizador.dto.TransacaoDTO;
import com.vr.miniautorizador.exception.CartaoInexistenteException;
import com.vr.miniautorizador.exception.SaldoInsuficienteException;
import com.vr.miniautorizador.exception.SenhaInvalidaException;
import com.vr.miniautorizador.exception.ValorNegativoException;
import com.vr.miniautorizador.repository.CartaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class TransacaoValidatorTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @InjectMocks
    private TransacaoValidator transacaoValidator;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testValidarTransacao() {
        TransacaoDTO transacaoDTO = new TransacaoDTO("1234567890", "1234", new BigDecimal(100));
        Cartao cartao = new Cartao("1234567890", "1234");

        when(cartaoRepository.findByNumeroCartao(transacaoDTO.numeroCartao())).thenReturn(Optional.of(cartao));

        transacaoValidator.validarTransacao(transacaoDTO);
    }

    @Test
    public void testValidarTransacao_CartaoInexistente() {
        TransacaoDTO transacaoDTO = new TransacaoDTO("1234567890", "1234", new BigDecimal(100));

        when(cartaoRepository.findByNumeroCartao(transacaoDTO.numeroCartao())).thenReturn(Optional.empty());

        assertThrows(CartaoInexistenteException.class, () -> transacaoValidator.validarTransacao(transacaoDTO));
    }

    @Test
    public void testValidarTransacao_SenhaInvalida() {
        TransacaoDTO transacaoDTO = new TransacaoDTO("1234567890", "1234", new BigDecimal(100));
        Cartao cartao = new Cartao("1234567890", "wrong_password");

        when(cartaoRepository.findByNumeroCartao(transacaoDTO.numeroCartao())).thenReturn(Optional.of(cartao));

        assertThrows(SenhaInvalidaException.class, () -> transacaoValidator.validarTransacao(transacaoDTO));
    }

    @Test
    public void testValidarTransacao_SaldoInsuficiente() {
        TransacaoDTO transacaoDTO = new TransacaoDTO("1234567890", "1234", new BigDecimal(1000));
        Cartao cartao = new Cartao("1234567890", "1234");

        when(cartaoRepository.findByNumeroCartao(transacaoDTO.numeroCartao())).thenReturn(Optional.of(cartao));

        assertThrows(SaldoInsuficienteException.class, () -> transacaoValidator.validarTransacao(transacaoDTO));
    }

    @Test
    public void testValidarTransacao_ValorNegativo() {
        TransacaoDTO transacaoDTO = new TransacaoDTO("1234567890", "1234", new BigDecimal(-100));
        Cartao cartao = new Cartao("1234567890", "1234");

        when(cartaoRepository.findByNumeroCartao(transacaoDTO.numeroCartao())).thenReturn(Optional.of(cartao));

        assertThrows(ValorNegativoException.class, () -> transacaoValidator.validarTransacao(transacaoDTO));
    }
}
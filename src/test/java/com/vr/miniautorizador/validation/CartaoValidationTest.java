package com.vr.miniautorizador.validation;

import com.vr.miniautorizador.domain.Cartao;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CartaoValidationTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @InjectMocks
    private CartaoValidation cartaoValidation;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObterCartao_ComCartaoExistente() {
        String numeroCartao = "123456789";
        Cartao cartaoMock = new Cartao(numeroCartao, "1234");
        when(cartaoRepository.findByNumeroCartao(numeroCartao)).thenReturn(Optional.of(cartaoMock));
        Cartao cartao = cartaoValidation.obterCartao(numeroCartao);
        assertEquals(cartaoMock, cartao);
    }

    @Test
    public void testObterCartao_CartaoInexistente() {
        String numeroCartao = "123456789";
        when(cartaoRepository.findByNumeroCartao(numeroCartao)).thenReturn(Optional.empty());
        assertThrows(CartaoInexistenteException.class, () -> {
            cartaoValidation.obterCartao(numeroCartao);
        });
    }

    @Test
    public void testValidarSenha_Correta() {
        Cartao cartao = new Cartao("123456789", "1234");
        assertDoesNotThrow(() -> cartaoValidation.validarSenha(cartao, "1234"));
    }

    @Test
    public void testValidarSenha_Invalida() {
        Cartao cartao = new Cartao("123456789", "1234");
        assertThrows(SenhaInvalidaException.class, () -> cartaoValidation.validarSenha(cartao, "0000"));
    }

    @Test
    public void testValidarSaldo_Suficiente() {
        Cartao cartao = new Cartao("123456789", "1234");
        assertDoesNotThrow(() -> cartaoValidation.validarSaldo(cartao, BigDecimal.valueOf(500)));
    }

    @Test
    public void testValidarSaldo_Insuficiente() {
        Cartao cartao = new Cartao("123456789", "1234");
        assertThrows(SaldoInsuficienteException.class, () -> cartaoValidation.validarSaldo(cartao, BigDecimal.valueOf(1500)));
    }

    @Test
    public void testValidarSaldo_ValorNegativo() {
        Cartao cartao = new Cartao("123456789", "1234");
        assertThrows(ValorNegativoException.class, () -> cartaoValidation.validarSaldo(cartao, BigDecimal.valueOf(-500)));
    }
}

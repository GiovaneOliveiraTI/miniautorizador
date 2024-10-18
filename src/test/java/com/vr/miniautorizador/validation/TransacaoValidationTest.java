package com.vr.miniautorizador.validation;

import com.vr.miniautorizador.domain.Cartao;
import com.vr.miniautorizador.dto.TransacaoDTO;
import com.vr.miniautorizador.repository.CartaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class TransacaoValidationTest {

    @Mock
    private CartaoRepository cartaoRepository;

    @InjectMocks
    private TransacaoValidation transacaoValidation;

    @Mock
    private CartaoValidation cartaoValidation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testValidarTransacao() {
        TransacaoDTO transacaoDTO = new TransacaoDTO("1234567890", "1234", new BigDecimal(100));
        Cartao cartao = new Cartao("1234567890", "1234");

        when(cartaoRepository.findByNumeroCartao(transacaoDTO.numeroCartao())).thenReturn(Optional.of(cartao));

        transacaoValidation.validarTransacao(transacaoDTO);
    }

}
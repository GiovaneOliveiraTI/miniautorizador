package com.vr.miniautorizador.exception;

import com.vr.miniautorizador.exception.enums.ErroAutorizacao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartaoInexistenteExceptionTest {

    @Test
    public void testGetErro() {
        String numeroCartao = "1234567890";
        CartaoInexistenteException exception = new CartaoInexistenteException(numeroCartao);
        assertEquals(ErroAutorizacao.CARTAO_INEXISTENTE, exception.getErro());
    }

    @Test
    public void testMessage() {
        String numeroCartao = "1234567890";
        CartaoInexistenteException exception = new CartaoInexistenteException(numeroCartao);
        assertEquals(String.format(ErroAutorizacao.CARTAO_INEXISTENTE.getMensagem(), numeroCartao), exception.getMessage());
    }
}
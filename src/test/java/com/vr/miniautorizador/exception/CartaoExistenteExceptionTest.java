package com.vr.miniautorizador.exception;

import com.vr.miniautorizador.exception.enums.ErroAutorizacao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartaoExistenteExceptionTest {

    @Test
    public void testGetErro() {
        String numeroCartao = "1234567890";
        CartaoExistenteException exception = new CartaoExistenteException(numeroCartao);
        assertEquals(ErroAutorizacao.CARTAO_EXISTENTE, exception.getErro());
    }

    @Test
    public void testMessage() {
        String numeroCartao = "1234567890";
        CartaoExistenteException exception = new CartaoExistenteException(numeroCartao);
        assertEquals(String.format(ErroAutorizacao.CARTAO_EXISTENTE.getMensagem(), numeroCartao), exception.getMessage());
    }
}
package com.vr.miniautorizador.exception;

import com.vr.miniautorizador.exception.enums.ErroAutorizacao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SenhaInvalidaExceptionTest {

    @Test
    public void testGetErro() {
        SenhaInvalidaException exception = new SenhaInvalidaException();
        assertEquals(ErroAutorizacao.SENHA_INVALIDA, exception.getErro());
    }

    @Test
    public void testMessage() {
        SenhaInvalidaException exception = new SenhaInvalidaException();
        assertEquals(ErroAutorizacao.SENHA_INVALIDA.getMensagem(), exception.getMessage());
    }
}
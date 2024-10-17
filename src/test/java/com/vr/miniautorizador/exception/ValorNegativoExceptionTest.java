package com.vr.miniautorizador.exception;

import com.vr.miniautorizador.exception.enums.ErroAutorizacao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValorNegativoExceptionTest {

    @Test
    public void testGetErro() {
        ValorNegativoException exception = new ValorNegativoException();
        assertEquals(ErroAutorizacao.VALOR_NEGATIVO, exception.getErro());
    }

    @Test
    public void testMessage() {
        ValorNegativoException exception = new ValorNegativoException();
        assertEquals(ErroAutorizacao.VALOR_NEGATIVO.getMensagem(), exception.getMessage());
    }
}
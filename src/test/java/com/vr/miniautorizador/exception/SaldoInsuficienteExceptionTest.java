package com.vr.miniautorizador.exception;

import com.vr.miniautorizador.exception.enums.ErroAutorizacao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaldoInsuficienteExceptionTest {

    @Test
    public void testGetErro() {
        SaldoInsuficienteException exception = new SaldoInsuficienteException();
        assertEquals(ErroAutorizacao.SALDO_INSUFICIENTE, exception.getErro());
    }

    @Test
    public void testMessage() {
        SaldoInsuficienteException exception = new SaldoInsuficienteException();
        assertEquals(ErroAutorizacao.SALDO_INSUFICIENTE.getMensagem(), exception.getMessage());
    }
}
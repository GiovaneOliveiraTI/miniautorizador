package com.vr.miniautorizador.exception.handler;

import com.vr.miniautorizador.exception.*;
import com.vr.miniautorizador.exception.enums.ErroAutorizacao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    @Mock
    private SenhaInvalidaException senhaInvalidaException;
    @Mock
    private CartaoInexistenteException cartaoInexistenteException;
    @Mock
    private CartaoExistenteException cartaoExistenteException;
    @Mock
    private SaldoInsuficienteException saldoInsuficienteException;
    @Mock
    private ValorNegativoException valorNegativoException;
    private GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    public void handleCartaoInexistenteTest() {
        when(cartaoInexistenteException.getErro()).thenReturn(ErroAutorizacao.CARTAO_INEXISTENTE);

        ResponseEntity<Map<String, Object>> responseEntity = globalExceptionHandler.handleCartaoInexistente(cartaoInexistenteException);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
        assertEquals(ErroAutorizacao.CARTAO_INEXISTENTE.name(), Objects.requireNonNull(responseEntity.getBody()).get("Body"));
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), responseEntity.getBody().get("Status Code"));
    }

    @Test
    public void handleSenhaInvalidaTest() {
        when(senhaInvalidaException.getErro()).thenReturn(ErroAutorizacao.SENHA_INVALIDA);

        ResponseEntity<Map<String, Object>> responseEntity = globalExceptionHandler.handleSenhaInvalida(senhaInvalidaException);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
        assertEquals(ErroAutorizacao.SENHA_INVALIDA.name(), Objects.requireNonNull(responseEntity.getBody()).get("Body"));
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), responseEntity.getBody().get("Status Code"));
    }

    @Test
    public void handleCartaoExistenteExceptionTest() {
        when(cartaoExistenteException.getErro()).thenReturn(ErroAutorizacao.CARTAO_EXISTENTE);

        ResponseEntity<Map<String, Object>> responseEntity = globalExceptionHandler.handleCartaoExistente(cartaoExistenteException);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
        assertEquals(ErroAutorizacao.CARTAO_EXISTENTE.name(), Objects.requireNonNull(responseEntity.getBody()).get("Body"));
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), responseEntity.getBody().get("Status Code"));
    }

    @Test
    public void handleSaldoInsuficienteExceptionTest() {
        when(saldoInsuficienteException.getErro()).thenReturn(ErroAutorizacao.SALDO_INSUFICIENTE);

        ResponseEntity<Map<String, Object>> responseEntity = globalExceptionHandler.handleSaldoInsuficiente(saldoInsuficienteException);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
        assertEquals(ErroAutorizacao.SALDO_INSUFICIENTE.name(), Objects.requireNonNull(responseEntity.getBody()).get("Body"));
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), responseEntity.getBody().get("Status Code"));
    }

    @Test
    public void handleValorNegativoExceptionTest() {
        when(valorNegativoException.getErro()).thenReturn(ErroAutorizacao.VALOR_NEGATIVO);

        ResponseEntity<Map<String, Object>> responseEntity = globalExceptionHandler.handleValorNegativo(valorNegativoException);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
        assertEquals(ErroAutorizacao.VALOR_NEGATIVO.name(), Objects.requireNonNull(responseEntity.getBody()).get("Body"));
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), responseEntity.getBody().get("Status Code"));
    }
}
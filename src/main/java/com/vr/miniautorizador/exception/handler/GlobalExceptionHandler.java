package com.vr.miniautorizador.exception.handler;

import com.vr.miniautorizador.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * A classe GlobalExceptionHandler estende ResponseEntityExceptionHandler para fornecer um manipulador de exceções global
 * para a aplicação. Isso permite que a aplicação lide com exceções específicas de maneira consistente.
 *
 * <p>Esta classe contém métodos para lidar com exceções específicas que podem ser lançadas pela aplicação.
 * Cada método é anotado com @ExceptionHandler e leva a classe de exceção específica como argumento.
 * Quando essa exceção é lançada, o método correspondente é chamado para lidar com ela.
 *
 * <p>Os métodos retornam um ResponseEntity que contém a mensagem de erro e o status HTTP apropriado.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final String statusCode = "Status Code";
    private final HttpStatus defaultHttpStatus = HttpStatus.UNPROCESSABLE_ENTITY;


    /**
     * Cria um ResponseEntity com base na exceção fornecida.
     * O ResponseEntity contém um Map com o status code e a mensagem de erro.
     *
     * @param ex a exceção a ser tratada.
     * @return ResponseEntity contendo o status code e a mensagem de erro.
     */
    private ResponseEntity<Map<String, Object>> createResponseEntity(AutorizadorException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put(statusCode, defaultHttpStatus.value());
        body.put("Body", ex.getErro().name());

        return new ResponseEntity<>(body, defaultHttpStatus);
    }

    /**
     * Manipula a exceção CartaoInexistenteException.
     * @param ex a exceção lançada.
     * @return ResponseEntity com a mensagem de erro e o status HTTP.
     */
    @ExceptionHandler(CartaoInexistenteException.class)
    public ResponseEntity<Map<String, Object>> handleCartaoInexistente(CartaoInexistenteException ex) {
        return createResponseEntity(ex);
    }
    /**
     * Manipula a exceção SenhaInvalidaException.
     * @param ex a exceção lançada.
     * @return ResponseEntity com a mensagem de erro e o status HTTP.
     */
    @ExceptionHandler(SenhaInvalidaException.class)
    public ResponseEntity<Map<String, Object>> handleSenhaInvalida(SenhaInvalidaException ex) {
        return createResponseEntity(ex);
    }
    /**
     * Manipula a exceção SaldoInsuficienteException.
     * @param ex a exceção lançada.
     * @return ResponseEntity com a mensagem de erro e o status HTTP.
     */
    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<Map<String, Object>> handleSaldoInsuficiente(SaldoInsuficienteException ex) {
        return createResponseEntity(ex);
    }

    /**
     * Manipula a exceção CartaoExistenteException.
     * @param ex a exceção lançada.
     * @return ResponseEntity com a mensagem de erro e o status HTTP.
     */
    @ExceptionHandler(CartaoExistenteException.class)
    public ResponseEntity<Map<String, Object>> handleCartaoExistente(CartaoExistenteException ex) {
        return createResponseEntity(ex);
    }

    /**
     * Manipula a exceção ValorNegativoException.
     * @param ex a exceção lançada.
     * @return ResponseEntity com a mensagem de erro e o status HTTP.
     */
    @ExceptionHandler(ValorNegativoException.class)
    public ResponseEntity<Map<String, Object>> handleValorNegativo(ValorNegativoException ex) {
        return createResponseEntity(ex);
    }
}
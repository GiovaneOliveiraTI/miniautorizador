package com.vr.miniautorizador.exception;

import com.vr.miniautorizador.exception.enums.ErroAutorizacao;

/**
 * Representa uma exceção lançada quando a senha fornecida é inválida.
 * Esta exceção é uma subclasse de RuntimeException, portanto, é uma exceção não verificada.
 */
public class SenhaInvalidaException extends AutorizadorException {
    private static final ErroAutorizacao erro = ErroAutorizacao.SENHA_INVALIDA;

    /**
     * Construtor padrão para a exceção.
     * Utiliza a mensagem do erro SENHA_INVALIDA.
     */
    public SenhaInvalidaException() {
        super(String.format(erro.getMensagem()));
    }

    /**
     * Retorna o erro de autorização associado a esta exceção.
     * @return o erro de autorização.
     */
    public ErroAutorizacao getErro() {
        return erro;
    }
}
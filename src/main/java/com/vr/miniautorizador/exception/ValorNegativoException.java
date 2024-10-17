package com.vr.miniautorizador.exception;

import com.vr.miniautorizador.exception.enums.ErroAutorizacao;

/**
 * Representa uma exceção lançada quando um valor negativo é usado para uma transação.
 * Esta exceção é uma subclasse de RuntimeException, portanto, é uma exceção não verificada.
 */
public class ValorNegativoException extends AutorizadorException {
    private static final ErroAutorizacao erro = ErroAutorizacao.VALOR_NEGATIVO;

    /**
     * Construtor padrão para a exceção.
     * Utiliza a mensagem do erro VALOR_NEGATIVO.
     */
    public ValorNegativoException() {
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
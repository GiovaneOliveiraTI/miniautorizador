package com.vr.miniautorizador.exception;

import com.vr.miniautorizador.exception.enums.ErroAutorizacao;

/**
 * Representa uma exceção lançada quando um cartão inexistente é tentado ser acessado.
 * Esta exceção é uma subclasse de RuntimeException, portanto, é uma exceção não verificada.
 */
public class CartaoInexistenteException extends AutorizadorException {
    private static final ErroAutorizacao erro = ErroAutorizacao.CARTAO_INEXISTENTE;

    /**
     * Construtor que recebe o número do cartão que causou a exceção.
     * @param numeroCartao o número do cartão que não foi encontrado.
     */
    public CartaoInexistenteException(String numeroCartao) {
        super(String.format(erro.getMensagem(), numeroCartao));
    }

    /**
     * Retorna o erro de autorização associado a esta exceção.
     * @return o erro de autorização.
     */
    public ErroAutorizacao getErro() {
        return erro;
    }
}
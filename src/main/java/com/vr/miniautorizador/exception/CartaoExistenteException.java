package com.vr.miniautorizador.exception;

import com.vr.miniautorizador.exception.enums.ErroAutorizacao;

/**
 * Representa uma exceção lançada quando um cartão já existente é tentado ser criado.
 * Esta exceção é uma subclasse de RuntimeException, portanto, é uma exceção não verificada.
 */
public class CartaoExistenteException extends AutorizadorException {
    private static final ErroAutorizacao erro = ErroAutorizacao.CARTAO_EXISTENTE;

    /**
     * Construtor que recebe o número do cartão que causou a exceção.
     * @param numeroCartao o número do cartão que já existe.
     */
    public CartaoExistenteException(String numeroCartao) {
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
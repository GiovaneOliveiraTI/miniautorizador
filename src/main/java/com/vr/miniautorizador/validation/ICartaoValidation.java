package com.vr.miniautorizador.validation;

import com.vr.miniautorizador.domain.Cartao;

import java.math.BigDecimal;

/**
 * Interface para o validador de cartões.
 * Esta interface define os métodos que devem ser implementados por qualquer classe que forneça validação para a entidade Cartao.
 */
public interface ICartaoValidation {

    /**
     * Obtém o cartão.
     *
     * @param numeroCartao O número do cartão a ser obtido.
     * @return O cartão obtido.
     */
    Cartao obterCartao(String numeroCartao);

    /**
     * Valida a senha do cartão.
     *
     * @param cartao O cartão cuja senha será validada.
     * @param senha A senha a ser validada.
     */
    void validarSenha(Cartao cartao, String senha);

    /**
     * Valida o saldo do cartão.
     *
     * @param cartao O cartão cujo saldo será validado.
     * @param valor O valor a ser validado.
     */
    void validarSaldo(Cartao cartao, BigDecimal valor);
}
package com.vr.miniautorizador.service;

import com.vr.miniautorizador.domain.Cartao;

import java.math.BigDecimal;

/**
 * Interface para o serviço de cartões.
 * Esta interface define os métodos que devem ser implementados por qualquer classe que forneça serviços para a entidade Cartao.
 */
public interface ICartaoService {

    /**
     * Debita um valor do saldo do cartão.
     *
     * @param cartao O cartão do qual o valor será debitado.
     * @param valor O valor a ser debitado do saldo do cartão.
     */
    void debitarSaldo(Cartao cartao, BigDecimal valor);

    /**
     * Salva o estado atual do cartão.
     *
     * @param cartao O cartão a ser salvo.
     */
    void salvarCartao(Cartao cartao);
}
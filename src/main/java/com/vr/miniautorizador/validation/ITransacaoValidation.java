package com.vr.miniautorizador.validation;

import com.vr.miniautorizador.dto.TransacaoDTO;

/**
 * Interface para o validador de transações.
 * Esta interface define o método que deve ser implementado por qualquer classe que forneça validação para a entidade Transacao.
 */
public interface ITransacaoValidation {

    /**
     * Valida a transação.
     *
     * @param transacaoDTO O DTO da transação a ser validada.
     */
    void validarTransacao(TransacaoDTO transacaoDTO);
}
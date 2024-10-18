package com.vr.miniautorizador.validation;

import com.vr.miniautorizador.domain.Cartao;
import com.vr.miniautorizador.dto.TransacaoDTO;
import org.springframework.stereotype.Component;

/**
 * Validador de transações.
 * Esta classe é responsável por validar as transações antes de serem processadas.
 */
@Component
public class TransacaoValidation implements ITransacaoValidation {


    private final CartaoValidation cartaoValidation;


    /**
     * Construtor para a classe TransacaoValidation.
     *
     * @param cartaoValidation cartões.
     */
    public TransacaoValidation(CartaoValidation cartaoValidation) {
        this.cartaoValidation = cartaoValidation;
    }

    /**
     * Valida a transação.
     * Este método valida a senha, o saldo e a existência do cartão.
     *
     * @param transacaoDTO O DTO da transação a ser validada.
     */
    public void validarTransacao(TransacaoDTO transacaoDTO) {
        Cartao cartao = cartaoValidation.obterCartao(transacaoDTO.numeroCartao());
        cartaoValidation.validarSenha(cartao, transacaoDTO.senha());
        cartaoValidation.validarSaldo(cartao, transacaoDTO.valor());
    }




}
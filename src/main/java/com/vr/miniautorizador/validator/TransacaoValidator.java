package com.vr.miniautorizador.validator;

import com.vr.miniautorizador.exception.SaldoInsuficienteException;
import com.vr.miniautorizador.dto.TransacaoDTO;
import com.vr.miniautorizador.exception.CartaoInexistenteException;
import com.vr.miniautorizador.exception.SenhaInvalidaException;
import com.vr.miniautorizador.domain.Cartao;
import com.vr.miniautorizador.exception.ValorNegativoException;
import com.vr.miniautorizador.repository.CartaoRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Validador de transações.
 * Esta classe é responsável por validar as transações antes de serem processadas.
 */
@Component
public class TransacaoValidator implements ITransacaoValidator {

    private final CartaoRepository cartaoRepository;

    /**
     * Construtor para a classe TransacaoValidator.
     *
     * @param cartaoRepository O repositório de cartões.
     */
    public TransacaoValidator(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    /**
     * Valida a transação.
     * Este método valida a senha, o saldo e a existência do cartão.
     *
     * @param transacaoDTO O DTO da transação a ser validada.
     */
    public void validarTransacao(TransacaoDTO transacaoDTO) {
        Cartao cartao = obterCartao(transacaoDTO.numeroCartao());
        validarSenha(cartao, transacaoDTO.senha());
        validarSaldo(cartao, transacaoDTO.valor());
    }

    /**
     * Obtém o cartão.
     * Este método busca o cartão pelo seu número.
     *
     * @param numeroCartao O número do cartão a ser buscado.
     * @return O cartão encontrado.
     * @throws CartaoInexistenteException Se nenhum cartão com o número fornecido for encontrado.
     */
    private Cartao obterCartao(String numeroCartao) {
        return cartaoRepository.findByNumeroCartao(numeroCartao)
                .orElseThrow(() -> new CartaoInexistenteException(numeroCartao));
    }

    /**
     * Valida a senha do cartão.
     * Este método verifica se a senha fornecida é igual à senha do cartão.
     *
     * @param cartao O cartão a ser validado.
     * @param senha A senha a ser validada.
     * @throws SenhaInvalidaException Se a senha fornecida não for igual à senha do cartão.
     */
    private void validarSenha(Cartao cartao, String senha) {
        if (!cartao.getSenha().equals(senha)) {
            throw new SenhaInvalidaException();
        }
    }

    /**
     * Valida o saldo do cartão.
     * Este método verifica se o saldo do cartão é suficiente para a transação.
     *
     * @param cartao O cartão a ser validado.
     * @param valor O valor da transação.
     * @throws ValorNegativoException Se o valor da transação for negativo.
     * @throws SaldoInsuficienteException Se o saldo do cartão for insuficiente para a transação.
     */
    private void validarSaldo(Cartao cartao, BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValorNegativoException();
        }

        if (cartao.getSaldo().compareTo(valor) < 0) {
            throw new SaldoInsuficienteException();
        }
    }
}
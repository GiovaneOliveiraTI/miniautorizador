package com.vr.miniautorizador.validation;

import com.vr.miniautorizador.domain.Cartao;
import com.vr.miniautorizador.exception.*;
import com.vr.miniautorizador.repository.CartaoRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Validation de cartao.
 * Esta classe é responsável por validar a informacoes de cartao.
 */
@Component
public class CartaoValidation implements ICartaoValidation{

    private final CartaoRepository cartaoRepository;

    /**
     * Construtor para a classe cartaoValidation.
     *
     * @param cartaoRepository O repositório de cartões.
     */
    public CartaoValidation(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    /**
     * Obtém o cartão.
     * Este método busca o cartão pelo seu número.
     *
     * @param numeroCartao O número do cartão a ser buscado.
     * @return O cartão encontrado.
     * @throws CartaoInexistenteException Se nenhum cartão com o número fornecido for encontrado.
     */
    public Cartao obterCartao(String numeroCartao) {
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
    public void validarSenha(Cartao cartao, String senha) {
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
    public void validarSaldo(Cartao cartao, BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValorNegativoException();
        }

        if (cartao.getSaldo().compareTo(valor) < 0) {
            throw new SaldoInsuficienteException();
        }
    }

    /**
     * Verifica se um Cartão com o número fornecido já existe.
     * @param numeroCartao o número do cartão a ser verificado.
     * @throws CartaoExistenteException se o cartão já existir.
     */
    public void verificarCartaoExistente(String numeroCartao) {
        cartaoRepository.findByNumeroCartao(numeroCartao)
                .ifPresent(cartao -> {
                    throw new CartaoExistenteException(numeroCartao);
                });
    }
}
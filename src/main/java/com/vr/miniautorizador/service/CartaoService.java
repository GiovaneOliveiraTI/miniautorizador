package com.vr.miniautorizador.service;

import com.vr.miniautorizador.domain.Cartao;
import com.vr.miniautorizador.dto.CartaoDTO;
import com.vr.miniautorizador.exception.CartaoExistenteException;
import com.vr.miniautorizador.exception.CartaoInexistenteException;
import com.vr.miniautorizador.repository.CartaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Serviço para operações relacionadas à entidade Cartão.
 * Este serviço utiliza o CartaoRepository para realizar suas operações.
 */
@Service
public class CartaoService implements ICartaoService {

    private final CartaoRepository cartaoRepository;

    /**
     * Construtor que recebe o CartaoRepository.
     * @param cartaoRepository o repositório a ser utilizado pelo serviço.
     */
    public CartaoService(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    /**
     * Cria um novo Cartão com base no CartaoDTO fornecido.
     * @param cartaoDTO o DTO contendo as informações do novo cartão.
     * @throws CartaoExistenteException se o cartão já existir.
     */
    public void criarCartao(CartaoDTO cartaoDTO) {
        verificarCartaoExistente(cartaoDTO.numeroCartao());
        Cartao novoCartao = new Cartao(cartaoDTO.numeroCartao(), cartaoDTO.senha());
        cartaoRepository.save(novoCartao);
    }

    /**
     * Verifica se um Cartão com o número fornecido já existe.
     * @param numeroCartao o número do cartão a ser verificado.
     * @throws CartaoExistenteException se o cartão já existir.
     */
    private void verificarCartaoExistente(String numeroCartao) {
        cartaoRepository.findByNumeroCartao(numeroCartao)
                .ifPresent(cartao -> {
                    throw new CartaoExistenteException(numeroCartao);
                });
    }

    /**
     * Consulta o saldo de um Cartão pelo seu número.
     * @param numeroCartao o número do cartão a ser consultado.
     * @return o saldo do cartão.
     * @throws CartaoInexistenteException se o cartão não for encontrado.
     */
    public BigDecimal consultarSaldo(String numeroCartao) {
        return obterCartaoPorNumero(numeroCartao).getSaldo();
    }

    /**
     * Obtém um Cartão pelo seu número.
     * @param numeroCartao o número do cartão a ser obtido.
     * @return o Cartão encontrado.
     * @throws CartaoInexistenteException se o cartão não for encontrado.
     */
    private Cartao obterCartaoPorNumero(String numeroCartao) {
        return cartaoRepository.findByNumeroCartao(numeroCartao)
                .orElseThrow(() -> new CartaoInexistenteException(numeroCartao));
    }

    /**
     * Debita um valor do saldo de um Cartão.
     * @param cartao o cartão a ter o saldo debitado.
     * @param valor o valor a ser debitado.
     */
    public void debitarSaldo(Cartao cartao, BigDecimal valor) {
        cartao.debitarSaldo(valor);
    }

    /**
     * Salva um Cartão no repositório.
     * @param cartao o cartão a ser salvo.
     */
    public void salvarCartao(Cartao cartao) {
        cartaoRepository.save(cartao);
    }
}
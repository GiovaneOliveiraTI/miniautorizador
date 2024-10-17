package com.vr.miniautorizador.service;

import com.vr.miniautorizador.domain.Cartao;
import com.vr.miniautorizador.exception.CartaoInexistenteException;
import com.vr.miniautorizador.repository.CartaoRepository;
import org.springframework.stereotype.Service;

/**
 * Serviço para consultas relacionadas a cartões.
 * Este serviço utiliza o CartaoRepository para realizar suas operações.
 */
@Service
public class CartaoQueryService implements ICartaoQueryService {

    private final CartaoRepository cartaoRepository;

    /**
     * Construtor que recebe o CartaoRepository.
     * @param cartaoRepository o repositório a ser utilizado pelo serviço.
     */
    public CartaoQueryService(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    /**
     * Busca um Cartão pelo seu número.
     * @param numeroCartao o número do cartão a ser buscado.
     * @return o Cartão encontrado.
     * @throws CartaoInexistenteException se o cartão não for encontrado.
     */
    public Cartao findByNumeroCartao(String numeroCartao) {
        return cartaoRepository.findByNumeroCartao(numeroCartao)
                .orElseThrow(() -> new CartaoInexistenteException(numeroCartao));
    }
}
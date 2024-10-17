package com.vr.miniautorizador.service;

import com.vr.miniautorizador.domain.Cartao;

/**
 * Interface para o serviço de consulta de cartões.
 * Esta interface define os métodos que devem ser implementados por qualquer classe que forneça serviços de consulta para a entidade Cartao.
 */
public interface ICartaoQueryService {

    /**
     * implementacao busca cartão pelo seu número.
     * @param numeroCartao O número do cartão a ser buscado.
     */
    Cartao findByNumeroCartao(String numeroCartao);
}
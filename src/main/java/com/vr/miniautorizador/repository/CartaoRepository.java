package com.vr.miniautorizador.repository;

import com.vr.miniautorizador.domain.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface de repositório para a entidade Cartão.
 * Esta interface estende JpaRepository, permitindo a realização de operações CRUD na entidade Cartão.
 * O JpaRepository é um componente do Spring Data JPA que fornece métodos de repositório JPA.
 */

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    /**
     * Busca um Cartão pelo seu número.
     * @param numeroCartao o número do cartão a ser buscado.
     * @return um Optional que pode conter o Cartão se ele for encontrado.
     */
    Optional<Cartao> findByNumeroCartao(String numeroCartao);
}
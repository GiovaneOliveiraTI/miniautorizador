package com.vr.miniautorizador.domain;

import com.vr.miniautorizador.exception.SaldoInsuficienteException;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Representa uma entidade Cartão no sistema.
 * Cada cartão possui um número único, uma senha e um saldo.
 */
@Entity
@Getter
@Setter
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numeroCartao;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private BigDecimal saldo;

    // Constante para o saldo inicial
    private static final BigDecimal SALDO_INICIAL = BigDecimal.valueOf(500.00);

    /**
     * Construtor principal para criar um novo Cartão.
     * @param numeroCartao o número único do cartão.
     * @param senha a senha do cartão.
     */
    public Cartao(String numeroCartao, String senha) {
        this.numeroCartao = numeroCartao;
        this.senha = senha;
        this.saldo = SALDO_INICIAL;
    }

    /**
     * Construtor vazio para JPA.
     * Define o saldo inicial para garantir consistência ao criar.
     */
    public Cartao() {
        this.saldo = SALDO_INICIAL;
    }

    /**
     * Debita o valor especificado do saldo do cartão.
     * @param valor o valor a ser debitado.
     */
    public void debitarSaldo(BigDecimal valor) {
        verificarSaldoSuficiente(valor);
        this.saldo = this.saldo.subtract(valor);
    }

    /**
     * Verifica se o cartão tem saldo suficiente para uma transação.
     * @param valor o valor a ser verificado.
     * @throws SaldoInsuficienteException se o saldo for insuficiente.
     */
    private void verificarSaldoSuficiente(BigDecimal valor) {
        Optional.of(this.saldo)
                .filter(saldo -> saldo.compareTo(valor) >= 0)
                .orElseThrow(() -> new SaldoInsuficienteException());
    }

}
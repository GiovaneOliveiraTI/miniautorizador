package com.vr.miniautorizador.dto;


import java.math.BigDecimal;


/**
 * Representa um Data Transfer Object (DTO) para uma Transação.
 * Este DTO contém o número do cartão, a senha e o valor da transação, que são usados para transferir dados entre camadas do sistema.
 */
public record TransacaoDTO(String numeroCartao, String senha, BigDecimal valor) {}

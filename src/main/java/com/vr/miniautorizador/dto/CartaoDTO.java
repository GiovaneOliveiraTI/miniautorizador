package com.vr.miniautorizador.dto;

/**
 * Representa um Data Transfer Object (DTO) para a entidade Cartão.
 * Este DTO contém o número do cartão e a senha, que são usados para transferir dados entre camadas do sistema.
 */
public record CartaoDTO(String numeroCartao, String senha) {
}
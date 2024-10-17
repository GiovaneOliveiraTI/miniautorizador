package com.vr.miniautorizador.exception.enums;

/**
 * Enumeração que representa os erros de autorização no sistema.
 * Cada erro tem uma mensagem associada que pode ser recuperada e exibida.
 */
public enum ErroAutorizacao {
    /**
     * Erro quando o saldo é insuficiente.
     */
    SALDO_INSUFICIENTE("Saldo insuficiente."),

    /**
     * Erro quando a senha é inválida.
     */
    SENHA_INVALIDA("Senha inválida."),

    /**
     * Erro quando o cartão não é encontrado.
     */
    CARTAO_INEXISTENTE("Cartão não encontrado."),

    /**
     * Erro quando o valor é negativo.
     */
    VALOR_NEGATIVO("O valor não pode ser negativo."),

    /**
     * Erro quando o cartão já existe.
     */
    CARTAO_EXISTENTE("Cartão com número %s já existe.");

    private final String mensagem;

    /**
     * Construtor que recebe a mensagem do erro.
     * @param mensagem a mensagem do erro.
     */
    ErroAutorizacao(String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * Retorna a mensagem associada ao erro.
     * @return a mensagem do erro.
     */
    public String getMensagem() {
        return mensagem;
    }
}
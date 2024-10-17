package com.vr.miniautorizador.exception;

import com.vr.miniautorizador.exception.enums.ErroAutorizacao;

/**
 * Representa uma exceção lançada quando o saldo é insuficiente para uma transação.
 * Esta exceção é uma subclasse de RuntimeException, portanto, é uma exceção não verificada.
 */
public class SaldoInsuficienteException extends AutorizadorException {
    private static final ErroAutorizacao erro = ErroAutorizacao.SALDO_INSUFICIENTE;

    /**
     * Construtor padrão para a exceção.
     * Utiliza a mensagem do erro SALDO_INSUFICIENTE.
     */
    public SaldoInsuficienteException() {
        super(String.format(erro.getMensagem()));
    }

    /**
     * Retorna o erro de autorização associado a esta exceção.
     * @return o erro de autorização.
     */
    public ErroAutorizacao getErro() {
        return erro;
    }
}
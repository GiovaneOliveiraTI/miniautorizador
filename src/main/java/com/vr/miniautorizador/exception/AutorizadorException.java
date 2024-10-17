/**
 * A classe AutorizadorException é uma superclasse abstrata para todas as exceções personalizadas na aplicação.
 * Ela estende RuntimeException, o que significa que é uma exceção não verificada.
 *
 * <p>Esta classe contém um método abstrato getErro() que deve ser implementado por todas as subclasses.
 * Este método é usado para obter a mensagem de erro específica associada a cada exceção.
 */
package com.vr.miniautorizador.exception;

import com.vr.miniautorizador.exception.enums.ErroAutorizacao;

public abstract class AutorizadorException extends RuntimeException {
    public AutorizadorException(String message) {
        super(message);
    }

    public abstract ErroAutorizacao getErro();
}
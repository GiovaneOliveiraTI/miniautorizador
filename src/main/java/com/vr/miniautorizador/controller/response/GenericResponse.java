package com.vr.miniautorizador.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericResponse<T> {

    private String mensagem;
    private int statusCode;
    private T body;

    public GenericResponse(String mensagem, int statusCode, T body) {
        this.mensagem = mensagem;
        this.statusCode = statusCode;
        this.body = body;
    }

}
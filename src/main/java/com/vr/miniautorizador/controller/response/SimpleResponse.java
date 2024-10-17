package com.vr.miniautorizador.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleResponse {

    private String mensagem;
    private int statusCode;
    private String body;

    public SimpleResponse(String mensagem, int statusCode, String body) {
        this.mensagem = mensagem;
        this.statusCode = statusCode;
        this.body = body;
    }

}
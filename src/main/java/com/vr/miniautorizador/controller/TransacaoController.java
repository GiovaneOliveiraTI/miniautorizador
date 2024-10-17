package com.vr.miniautorizador.controller;

import com.vr.miniautorizador.controller.response.SimpleResponse;
import com.vr.miniautorizador.dto.TransacaoDTO;
import com.vr.miniautorizador.service.TransacaoService;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransacaoController {

    private final TransacaoService transacaoService;


    public static final int HTTP_STATUS_CREATED = HttpStatus.CREATED.value();

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }
    @PostMapping("/transacoes")
    public ResponseEntity<SimpleResponse> realizarTransacao(@RequestBody TransacaoDTO transacaoDTO) {
        transacaoService.realizarTransacao(transacaoDTO);
        SimpleResponse response = new SimpleResponse("Transação realizada com sucesso",
                HTTP_STATUS_CREATED, "OK");
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.CONTENT_TYPE,
                MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE).body(response);
    }
}
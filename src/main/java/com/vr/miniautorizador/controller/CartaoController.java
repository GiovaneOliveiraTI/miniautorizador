package com.vr.miniautorizador.controller;

import com.vr.miniautorizador.controller.response.GenericResponse;
import com.vr.miniautorizador.dto.CartaoDTO;
import com.vr.miniautorizador.service.CartaoService;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    private final CartaoService cartaoService;

    public static final int HTTP_STATUS_CREATED = HttpStatus.CREATED.value();
    public static final int HTTP_STATUS_OK = HttpStatus.CREATED.value();

    public CartaoController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @PostMapping
    public ResponseEntity<GenericResponse<CartaoDTO>> criarCartao(@RequestBody CartaoDTO cartaoDTO) {
        cartaoService.criarCartao(cartaoDTO);
        GenericResponse<CartaoDTO> response = new GenericResponse<>("Criação com sucesso",
                HTTP_STATUS_CREATED, cartaoDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(response);
    }

    @GetMapping("/{numeroCartao}")
    public ResponseEntity<GenericResponse<String>> consultarSaldo(@PathVariable String numeroCartao) {
        BigDecimal saldo = cartaoService.consultarSaldo(numeroCartao);
        GenericResponse<String> response = new GenericResponse<>("Obtenção com sucesso",
                HTTP_STATUS_OK, saldo.toString());
        return ResponseEntity.ok(response);
    }


}

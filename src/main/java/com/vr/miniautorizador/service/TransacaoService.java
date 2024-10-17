package com.vr.miniautorizador.service;

import com.vr.miniautorizador.domain.Cartao;
import com.vr.miniautorizador.dto.TransacaoDTO;
import com.vr.miniautorizador.validator.ITransacaoValidator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável por gerenciar transações.
 * Este serviço valida a transação, busca o cartão associado, debita o saldo e salva o estado do cartão.
 */
@Service
public class TransacaoService {

    private final ITransacaoValidator transacaoValidator;
    private final ICartaoQueryService cartaoQueryService;
    private final ICartaoService cartaoService;

    /**
     * Construtor para a classe TransacaoService.
     *
     * @param transacaoValidator O validador de transações.
     * @param cartaoQueryService O serviço de consulta de cartões.
     * @param cartaoService O serviço de cartões.
     */
    public TransacaoService(ITransacaoValidator transacaoValidator, ICartaoQueryService cartaoQueryService,
                            ICartaoService cartaoService) {
        this.transacaoValidator = transacaoValidator;
        this.cartaoQueryService = cartaoQueryService;
        this.cartaoService = cartaoService;
    }

    /**
     * Realiza uma transação.
     * Este método valida a transação, busca o cartão associado, debita o saldo e salva o estado do cartão.
     *
     * @param transacaoDTO O DTO da transação a ser realizada.
     * @return O DTO da transação realizada.
     */
    @Transactional
    public TransacaoDTO realizarTransacao(TransacaoDTO transacaoDTO) {
        validarTransacao(transacaoDTO);
        Cartao cartao = buscarCartao(transacaoDTO);
        debitarSaldo(cartao, transacaoDTO);
        salvarCartao(cartao);
        return transacaoDTO;
    }

    /**
     * Valida a transação.
     * Este método utiliza o validador de transações para validar a transação.
     *
     * @param transacaoDTO O DTO da transação a ser validada.
     */
    private void validarTransacao(TransacaoDTO transacaoDTO) {
        transacaoValidator.validarTransacao(transacaoDTO);
    }

    /**
     * Busca o cartão associado à transação.
     * Este método utiliza o serviço de consulta de cartões para buscar o cartão.
     *
     * @param transacaoDTO O DTO da transação.
     * @return O cartão associado à transação.
     */
    private Cartao buscarCartao(TransacaoDTO transacaoDTO) {
        return cartaoQueryService.findByNumeroCartao(transacaoDTO.numeroCartao());
    }

    /**
     * Debita o saldo do cartão.
     * Este método utiliza o serviço de cartões para debitar o saldo do cartão.
     *
     * @param cartao O cartão do qual o saldo será debitado.
     * @param transacaoDTO O DTO da transação.
     */
    private void debitarSaldo(Cartao cartao, TransacaoDTO transacaoDTO) {
        cartaoService.debitarSaldo(cartao, transacaoDTO.valor());
    }

    /**
     * Salva o estado do cartão.
     * Este método utiliza o serviço de cartões para salvar o estado do cartão.
     *
     * @param cartao O cartão a ser salvo.
     */
    private void salvarCartao(Cartao cartao) {
        cartaoService.salvarCartao(cartao);
    }
}
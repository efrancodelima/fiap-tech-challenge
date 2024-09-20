package br.com.fiap.tech_challenge.interface_layer.controllers.adapters.request_adapters;

import java.time.LocalDateTime;

import br.com.fiap.tech_challenge.application_layer.exceptions.ApplicationException;
import br.com.fiap.tech_challenge.business_layer.entities.pedido.StatusPagamento;
import br.com.fiap.tech_challenge.business_layer.entities.pedido.StatusPagamentoEnum;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.mercado_pago.PagamentoDto;

public final class PagamentoRequestAdapter {

    public static StatusPagamento adaptar(PagamentoDto pagamentoDto) throws Exception {

        Long codigo = pagamentoDto.getId();
        StatusPagamentoEnum status = adaptarStatus(pagamentoDto.getStatus());
        LocalDateTime dataHora = LocalDateTime.now();

        return new StatusPagamento(codigo, status, dataHora);
    }

    private static StatusPagamentoEnum adaptarStatus(String statusRequisicao) throws Exception {

        statusRequisicao = statusRequisicao == null ? "" : statusRequisicao.trim().toUpperCase();

        switch (statusRequisicao) {
            case "PENDING":
            case "IN_PROCESS":
                return StatusPagamentoEnum.AGUARDANDO_PAGAMENTO;
            case "APPROVED":
                return StatusPagamentoEnum.APROVADO;
            case "REJECTED":
                return StatusPagamentoEnum.REPROVADO;
            default:
                // Retorna erro
                return StatusPagamentoEnum.fromString(statusRequisicao);
        }
    }
}

package br.com.fiap.tech_challenge.interface_layer.gateways.mappers;

import br.com.fiap.tech_challenge.business_layer.entities.pedido.StatusPagamento;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.StatusPagamentoJpa;

public final class StatusPagamentoMapper {

    public static StatusPagamentoJpa getStatusPagamentoJpa(StatusPagamento status) {
        return new StatusPagamentoJpa(status.getCodigo(), status.getStatus(), status.getDataHora());
    }

    public static StatusPagamento getStatusPagamento(StatusPagamentoJpa status)
            throws Exception {
        return new StatusPagamento(status.getStatus(), status.getDataHora());
    }

}

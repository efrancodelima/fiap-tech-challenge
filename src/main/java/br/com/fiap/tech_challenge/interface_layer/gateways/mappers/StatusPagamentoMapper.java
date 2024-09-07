package br.com.fiap.tech_challenge.interface_layer.gateways.mappers;

import br.com.fiap.tech_challenge.domain_layer.business_entities.StatusPagamento;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.StatusPagamentoJpa;

public final class StatusPagamentoMapper {

    public static StatusPagamentoJpa mapearParaEntidadeJpa(StatusPagamento status) {
        return new StatusPagamentoJpa(status.getStatus(), status.getDataHora());
    }

    public static StatusPagamento mapearParaEntidadeNegocio(StatusPagamentoJpa status)
            throws Exception {
        return new StatusPagamento(status.getStatus(), status.getDataHora());
    }

}

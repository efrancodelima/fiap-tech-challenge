package br.com.fiap.tech_challenge.interface_layer.gateways.jpa_mappers;

import br.com.fiap.tech_challenge.domain_layer.business_entities.StatusPagamento;
import br.com.fiap.tech_challenge.interface_layer.gateways.jpa_entities.StatusPagamentoJpa;

public final class StatusPagamentoMapper {

    public static StatusPagamentoJpa entidadeNegocioParaEntidadeJpa(StatusPagamento status) {
        return new StatusPagamentoJpa(status.getStatus(), status.getDataHora());
    }

    public static StatusPagamento entidadeJpaParaEntidadeNegocio(StatusPagamentoJpa status)
            throws Exception {
        return new StatusPagamento(status.getStatus(), status.getDataHora());
    }

}

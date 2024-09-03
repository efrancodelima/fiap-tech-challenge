package br.com.fiap.tech_challenge.interface_layer.gateways.adapters;

import br.com.fiap.tech_challenge.domain_layer.business_entities.StatusPedido;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.StatusPedidoJpa;

public final class StatusPedidoMapper {

    public static StatusPedidoJpa entidadeNegocioParaEntidadeJpa(StatusPedido status) {
        return new StatusPedidoJpa(status.getStatus(), status.getDataHora());
    }

    public static StatusPedido entidadeJpaParaEntidadeNegocio(StatusPedidoJpa status)
            throws Exception {
        return new StatusPedido(status.getStatus(), status.getDataHora());
    }

}

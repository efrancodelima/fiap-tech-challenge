package br.com.fiap.tech_challenge.interface_layer.gateways.mappers;

import br.com.fiap.tech_challenge.business_layer.entities.StatusPedido;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.StatusPedidoJpa;

public final class StatusPedidoMapper {

    public static StatusPedidoJpa getStatusPedidoJpa(StatusPedido status) {
        return new StatusPedidoJpa(status.getStatus(), status.getDataHora());
    }

    public static StatusPedido getStatusPedido(StatusPedidoJpa status)
            throws Exception {
        return new StatusPedido(status.getStatus(), status.getDataHora());
    }

}

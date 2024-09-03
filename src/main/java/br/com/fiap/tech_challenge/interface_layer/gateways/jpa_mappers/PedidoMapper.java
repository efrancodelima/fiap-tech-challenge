package br.com.fiap.tech_challenge.interface_layer.gateways.jpa_mappers;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.ItemPedido;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;
import br.com.fiap.tech_challenge.domain_layer.business_entities.StatusPagamento;
import br.com.fiap.tech_challenge.domain_layer.business_entities.StatusPedido;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesExceptions;
import br.com.fiap.tech_challenge.interface_layer.gateways.jpa_entities.ClienteJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.jpa_entities.ItemPedidoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.jpa_entities.PedidoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.jpa_entities.StatusPagamentoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.jpa_entities.StatusPedidoJpa;

public final class PedidoMapper {

    // Métodos públicos
    public static PedidoJpa entidadeNegocioParaEntidadeJpa(Pedido pedido) {
        ClienteJpa clienteJpa = ClienteMapper.entidadeNegocioParaEntidadeJpa(pedido.getCliente());
        List<ItemPedidoJpa> itensJpa = ItemPedidoMapper.entidadesNegocioParaEntidadesJpa(pedido.getItens());
        StatusPagamentoJpa statusPagamentoJpa = StatusPagamentoMapper
                .entidadeNegocioParaEntidadeJpa(pedido.getStatusPagamento());
        StatusPedidoJpa statusPedidoJpa = StatusPedidoMapper.entidadeNegocioParaEntidadeJpa(pedido.getStatusPedido());

        return new PedidoJpa(pedido.getId(), clienteJpa, itensJpa, pedido.getDataHoraCheckout(),
                statusPagamentoJpa, statusPedidoJpa);
    }

    public static Pedido entidadeJpaParaEntidadeNegocio(PedidoJpa pedidoJpa) throws BusinessRulesExceptions {
        long id = pedidoJpa.getId();
        Cliente cliente = ClienteMapper.entidadeJpaParaEntidadeNegocio(pedidoJpa.getCliente());
        List<ItemPedido> itens = ItemPedidoMapper.entidadesJpaParaEntidadesNegocio(pedidoJpa.getItens());
        StatusPagamento statusPagamento = StatusPagamentoMapper
                .entidadeJpaParaEntidadeNegocio(pedidoJpa.getStatusPagamento());
        StatusPedido statusPedido = StatusPedidoMapper.entidadeJpaParaEntidadeNegocio(pedidoJpa.getStatusPedido());

        return new Pedido(id, cliente, itens, pedidoJpa.getDataHoraCheckout(), statusPagamento, statusPedido);
    }

    public static List<Pedido> entidadesJpaParaEntidadesNegocio(List<PedidoJpa> pedidosJpa)
            throws BusinessRulesExceptions {
        List<Pedido> pedidos = new ArrayList<>();
        for (PedidoJpa pedidoJpa : pedidosJpa) {
            Pedido pedido = entidadeJpaParaEntidadeNegocio(pedidoJpa);
            pedidos.add(pedido);
        }
        return pedidos;
    }

}

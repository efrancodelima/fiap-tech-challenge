package br.com.fiap.tech_challenge.interface_layer.gateways.mappers;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.ItemPedido;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;
import br.com.fiap.tech_challenge.domain_layer.business_entities.StatusPagamento;
import br.com.fiap.tech_challenge.domain_layer.business_entities.StatusPedido;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.ClienteJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.ItemPedidoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.PedidoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.StatusPagamentoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.StatusPedidoJpa;

public final class PedidoMapper {

    // Métodos públicos
    public static PedidoJpa entidadeNegocioParaEntidadeJpa(Pedido pedido) {
        ClienteJpa clienteJpa = ClienteMapper.mapperParaEntidadeJpa(pedido.getCliente());
        List<ItemPedidoJpa> itensJpa = ItemPedidoMapper.adaptarParaEntidadeJpa(pedido.getItens());
        StatusPagamentoJpa statusPagamentoJpa = StatusPagamentoMapper
                .entidadeNegocioParaEntidadeJpa(pedido.getStatusPagamento());
        StatusPedidoJpa statusPedidoJpa = StatusPedidoMapper.entidadeNegocioParaEntidadeJpa(pedido.getStatusPedido());

        return new PedidoJpa(pedido.getId(), clienteJpa, itensJpa, pedido.getDataHoraCheckout(),
                statusPagamentoJpa, statusPedidoJpa);
    }

    public static Pedido entidadeJpaParaEntidadeNegocio(PedidoJpa pedidoJpa) throws Exception {
        long id = pedidoJpa.getId();
        Cliente cliente = ClienteMapper.mapperParaEntidadeNegocio(pedidoJpa.getCliente());
        List<ItemPedido> itens = ItemPedidoMapper.adaptarParaEntidadeNegocio(pedidoJpa.getItens());
        StatusPagamento statusPagamento = StatusPagamentoMapper
                .entidadeJpaParaEntidadeNegocio(pedidoJpa.getStatusPagamento());
        StatusPedido statusPedido = StatusPedidoMapper.entidadeJpaParaEntidadeNegocio(pedidoJpa.getStatusPedido());

        return new Pedido(id, cliente, itens, pedidoJpa.getDataHoraCheckout(), statusPagamento, statusPedido);
    }

    public static List<Pedido> entidadesJpaParaEntidadesNegocio(List<PedidoJpa> pedidosJpa)
            throws Exception {
        List<Pedido> pedidos = new ArrayList<>();
        for (PedidoJpa pedidoJpa : pedidosJpa) {
            Pedido pedido = entidadeJpaParaEntidadeNegocio(pedidoJpa);
            pedidos.add(pedido);
        }
        return pedidos;
    }

}

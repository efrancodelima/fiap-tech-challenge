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
    public static PedidoJpa mapearParaEntidadeJpa(Pedido pedido) {
        ClienteJpa clienteJpa = ClienteMapper.mapearParaEntidadeJpa(pedido.getCliente());
        List<ItemPedidoJpa> itensJpa = ItemPedidoMapper.mapearParaEntidadeJpa(pedido.getItens());
        StatusPagamentoJpa statusPagamentoJpa = StatusPagamentoMapper
                .mapearParaEntidadeJpa(pedido.getStatusPagamento());
        StatusPedidoJpa statusPedidoJpa = StatusPedidoMapper.mapearParaEntidadeJpa(pedido.getStatusPedido());

        return new PedidoJpa(pedido.getNumero(), clienteJpa, itensJpa, pedido.getDataHoraCheckout(),
                statusPagamentoJpa, statusPedidoJpa);
    }

    public static Pedido mapearParaEntidadeNegocio(PedidoJpa pedidoJpa) throws Exception {
        long id = pedidoJpa.getNumero();
        Cliente cliente = ClienteMapper.mapearParaEntidadeNegocio(pedidoJpa.getCliente());
        List<ItemPedido> itens = ItemPedidoMapper.mapearParaEntidadeNegocio(pedidoJpa.getItens());
        StatusPagamento statusPagamento = StatusPagamentoMapper
                .mapearParaEntidadeNegocio(pedidoJpa.getStatusPagamento());
        StatusPedido statusPedido = StatusPedidoMapper.mapearParaEntidadeNegocio(pedidoJpa.getStatusPedido());

        return new Pedido(id, cliente, itens, pedidoJpa.getDataHoraCheckout(), statusPagamento, statusPedido);
    }

    public static List<Pedido> mapearParaEntidadesNegocio(List<PedidoJpa> pedidosJpa)
            throws Exception {
        List<Pedido> pedidos = new ArrayList<>();
        for (PedidoJpa pedidoJpa : pedidosJpa) {
            Pedido pedido = mapearParaEntidadeNegocio(pedidoJpa);
            pedidos.add(pedido);
        }
        return pedidos;
    }

}

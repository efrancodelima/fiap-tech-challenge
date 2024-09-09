package br.com.fiap.tech_challenge.interface_layer.gateways.mappers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.tech_challenge.business_layer.entities.Cliente;
import br.com.fiap.tech_challenge.business_layer.entities.ItemPedido;
import br.com.fiap.tech_challenge.business_layer.entities.Pedido;
import br.com.fiap.tech_challenge.business_layer.entities.StatusPagamento;
import br.com.fiap.tech_challenge.business_layer.entities.StatusPedido;
import br.com.fiap.tech_challenge.business_layer.entities.enums.StatusPedidoEnum;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.ClienteJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.ItemPedidoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.PedidoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.StatusPagamentoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.StatusPedidoJpa;

public final class PedidoMapper {

    // Métodos públicos
    public static PedidoJpa getPedidoJpa(Pedido pedido) {

        ClienteJpa clienteJpa = null;
        List<ItemPedidoJpa> itensJpa = ItemPedidoMapper.getListItemPedidoJpa(pedido.getItens());
        LocalDateTime dataHoraCheckout = pedido.getDataHoraCheckout();
        StatusPagamentoJpa statusPagamentoJpa = null;
        StatusPedidoJpa statusPedidoJpa;

        if (pedido.getCliente() != null) {
            clienteJpa = ClienteMapper.getClienteJpa(pedido.getCliente());
        }

        if (pedido.getStatusPagamento() != null) {
            statusPagamentoJpa = StatusPagamentoMapper
                    .getStatusPagamentoJpa(pedido.getStatusPagamento());
        }

        if (pedido.getStatusPedido() != null) {
            statusPedidoJpa = StatusPedidoMapper.getStatusPedidoJpa(pedido.getStatusPedido());
        } else {
            statusPedidoJpa = new StatusPedidoJpa(StatusPedidoEnum.AGUARDANDO_CHECKOUT, LocalDateTime.now());
        }

        return new PedidoJpa(pedido.getNumero(), clienteJpa, itensJpa, dataHoraCheckout,
                statusPagamentoJpa, statusPedidoJpa);
    }

    public static Pedido getPedido(PedidoJpa pedidoJpa) throws Exception {

        long id = pedidoJpa.getNumero();
        Cliente cliente = null;
        List<ItemPedido> itens = ItemPedidoMapper.getListItemPedido(pedidoJpa.getItensJpa());
        LocalDateTime dataHoraCheckout = pedidoJpa.getDataHoraCheckout();
        StatusPagamento statusPagamento = null;
        StatusPedido statusPedido = StatusPedidoMapper.getStatusPedido(pedidoJpa.getStatusPedido());

        if (pedidoJpa.getClienteJpa() != null) {
            cliente = ClienteMapper.getCliente(pedidoJpa.getClienteJpa());
        }

        if (pedidoJpa.getStatusPagamento() != null) {
            statusPagamento = StatusPagamentoMapper
                    .getStatusPagamento(pedidoJpa.getStatusPagamento());
        }

        return new Pedido(id, cliente, itens, dataHoraCheckout, statusPagamento, statusPedido);
    }

    public static List<Pedido> getListPedido(List<PedidoJpa> pedidosJpa)
            throws Exception {
        List<Pedido> pedidos = new ArrayList<>();
        for (PedidoJpa pedidoJpa : pedidosJpa) {
            Pedido pedido = getPedido(pedidoJpa);
            pedidos.add(pedido);
        }
        return pedidos;
    }

}

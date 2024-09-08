package br.com.fiap.tech_challenge.interface_layer.gateways.mappers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.domain_layer.business_entities.ItemPedido;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;
import br.com.fiap.tech_challenge.domain_layer.business_entities.StatusPagamento;
import br.com.fiap.tech_challenge.domain_layer.business_entities.StatusPedido;
import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.StatusPedidoEnum;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.ClienteJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.ItemPedidoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.PedidoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.StatusPagamentoJpa;
import br.com.fiap.tech_challenge.interface_layer.gateways.entities.StatusPedidoJpa;

public final class PedidoMapper {

    // Métodos públicos
    public static PedidoJpa mapearParaEntidadeJpa(Pedido pedido) {

        ClienteJpa clienteJpa = null;
        List<ItemPedidoJpa> itensJpa = ItemPedidoMapper.mapearParaEntidadeJpa(pedido.getItens());
        LocalDateTime dataHoraCheckout = pedido.getDataHoraCheckout();
        StatusPagamentoJpa statusPagamentoJpa = null;
        StatusPedidoJpa statusPedidoJpa;

        if (pedido.getCliente() != null) {
            clienteJpa = ClienteMapper.mapearParaEntidadeJpa(pedido.getCliente());
        }

        if (pedido.getStatusPagamento() != null) {
            statusPagamentoJpa = StatusPagamentoMapper
                    .mapearParaEntidadeJpa(pedido.getStatusPagamento());
        }

        if (pedido.getStatusPedido() != null) {
            statusPedidoJpa = StatusPedidoMapper.mapearParaEntidadeJpa(pedido.getStatusPedido());
        } else {
            statusPedidoJpa = new StatusPedidoJpa(StatusPedidoEnum.AGUARDANDO_CHECKOUT, LocalDateTime.now());
        }

        return new PedidoJpa(pedido.getNumero(), clienteJpa, itensJpa, dataHoraCheckout,
                statusPagamentoJpa, statusPedidoJpa);
    }

    public static Pedido mapearParaEntidadeNegocio(PedidoJpa pedidoJpa) throws Exception {

        long id = pedidoJpa.getNumero();
        Cliente cliente = null;
        List<ItemPedido> itens = ItemPedidoMapper.mapearParaEntidadeNegocio(pedidoJpa.getItens());
        LocalDateTime dataHoraCheckout = pedidoJpa.getDataHoraCheckout();
        StatusPagamento statusPagamento = null;
        StatusPedido statusPedido = StatusPedidoMapper.mapearParaEntidadeNegocio(pedidoJpa.getStatusPedido());

        if (pedidoJpa.getCliente() != null) {
            cliente = ClienteMapper.mapearParaEntidadeNegocio(pedidoJpa.getCliente());
        }

        if (pedidoJpa.getStatusPagamento() != null) {
            statusPagamento = StatusPagamentoMapper
                    .mapearParaEntidadeNegocio(pedidoJpa.getStatusPagamento());
        }

        return new Pedido(id, cliente, itens, dataHoraCheckout, statusPagamento, statusPedido);
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

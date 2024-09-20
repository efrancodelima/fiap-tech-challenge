package br.com.fiap.tech_challenge.application_layer.interfaces.gateway;

import java.util.List;

import br.com.fiap.tech_challenge.business_layer.entities.pedido.Pedido;

public interface IPedidoGateway {

    Pedido gravarPedido(Pedido pedido) throws Exception;

    void atualizarPedido(Pedido pedido) throws Exception;

    Pedido buscarPedido(long numeroPedido) throws Exception;

    Pedido buscarPedidoPeloCodigoPagamento(long codigoPagamento) throws Exception;

    List<Pedido> buscarTodosOsPedidos() throws Exception;

}

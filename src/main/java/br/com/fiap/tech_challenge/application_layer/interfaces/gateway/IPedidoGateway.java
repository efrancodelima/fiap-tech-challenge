package br.com.fiap.tech_challenge.application_layer.interfaces.gateway;

import java.util.List;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;

public interface IPedidoGateway {

    public Pedido gravarPedido(Pedido pedido) throws Exception;

    public void atualizarPedido(Pedido pedido) throws Exception;

    public void removerPedido(Pedido pedido) throws Exception;

    public Pedido buscarPedido(long numeroPedido) throws Exception;

    public List<Pedido> buscarPedidos() throws Exception;

}

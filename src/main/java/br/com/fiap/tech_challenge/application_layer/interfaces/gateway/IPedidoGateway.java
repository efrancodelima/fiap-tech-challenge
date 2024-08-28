package br.com.fiap.tech_challenge.application_layer.interfaces.gateway;

import java.util.ArrayList;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;

public interface IPedidoGateway {

    public Pedido gravarPedido(Pedido pedido);

    public Pedido atualizarPedido(Pedido pedido);

    public void removerPedido(Pedido pedido);

    public ArrayList<Pedido> listarPedidosAbertos();
}

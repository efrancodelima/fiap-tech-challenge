package br.com.fiap.tech_challenge.use_cases_layer.repository;

import java.util.ArrayList;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;

public interface PedidoRepository {
    public Pedido alterarPedido(Pedido pedido);

    public ArrayList<Pedido> listarPedidosAbertos();
}

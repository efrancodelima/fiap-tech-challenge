package br.com.fiap.tech_challenge.core.domain.ports.in;

import br.com.fiap.tech_challenge.core.domain.model.Pedido;

import java.util.List;

public interface PedidoServicePort {
    void cadastrarPedido(Pedido pedido);
    List<Pedido> listarPedidos();

}

package br.com.fiap.tech_challenge.application_layer.use_cases.interfaces;

import java.util.List;

import br.com.fiap.tech_challenge.business_layer.entities.Pedido;
import br.com.fiap.tech_challenge.business_layer.entities.StatusPagamento;

public interface IPedidoUseCase {

    Pedido fazerCheckout(Pedido pedido) throws Exception;

    Pedido atualizarStatusPedido(Long numeroPedido) throws Exception;

    StatusPagamento consultarStatusPagamento(Long numeroPedido) throws Exception;

    List<Pedido> listarPedidos() throws Exception;

    void atualizarStatusPagamento(StatusPagamento statusPagamento) throws Exception;

}

package br.com.fiap.tech_challenge.application_layer.interfaces.use_cases;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;
import br.com.fiap.tech_challenge.domain_layer.business_entities.StatusPagamento;

public interface IPedidoUseCase {

    Pedido fazerCheckout(Pedido pedido) throws Exception;

    Pedido atualizarStatusPedido(long numeroPedido) throws Exception;

    StatusPagamento consultarStatusPagamento(long numeroPedido) throws Exception;

}

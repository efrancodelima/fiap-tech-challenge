package br.com.fiap.tech_challenge.application_layer.interfaces.use_cases;

import java.util.List;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;

public interface IPedidoUseCase {

    Pedido fazerCheckout(Pedido pedido) throws Exception;

    void atualizarStatus(Pedido pedido) throws Exception;

    // Para usar com o webhook
    void confirmarPagamento(Pedido pedido, boolean pagamentoProcessado) throws Exception;

    boolean consultarStatusPagamento(Pedido pedido) throws Exception;

    List<Pedido> listarPedidos() throws Exception;

}

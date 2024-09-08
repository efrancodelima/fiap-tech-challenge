package br.com.fiap.tech_challenge.application_layer.use_cases;

import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IPedidoGateway;
import br.com.fiap.tech_challenge.application_layer.interfaces.use_cases.IPedidoUseCase;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;
import br.com.fiap.tech_challenge.domain_layer.business_entities.StatusPagamento;

public class PedidoUseCase implements IPedidoUseCase {

    // Atributos
    private IPedidoGateway gateway;

    // Construtor
    public PedidoUseCase(IPedidoGateway gateway) {
        this.gateway = gateway;
    }

    // Métodos
    @Override
    public Pedido fazerCheckout(Pedido pedido) throws Exception {
        pedido.fazerCheckout();
        return gateway.gravarPedido(pedido);
    }

    @Override
    public Pedido atualizarStatusPedido(long numeroPedido) throws Exception {
        Pedido pedido = gateway.buscarPedido(numeroPedido);
        pedido.atualizarStatusPedido();
        gateway.atualizarPedido(pedido);
        return pedido;
    }

    @Override
    public StatusPagamento consultarStatusPagamento(long numeroPedido) throws Exception {
        Pedido pedido = gateway.buscarPedido(numeroPedido);
        return pedido.getStatusPagamento();
    }

}

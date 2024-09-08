package br.com.fiap.tech_challenge.application_layer.use_cases;

import java.util.List;

import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IPedidoGateway;
import br.com.fiap.tech_challenge.application_layer.interfaces.use_cases.IPedidoUseCase;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;

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
    public void atualizarStatus(Pedido pedido) throws Exception {
        pedido.atualizarStatusPedido();
        gateway.atualizarPedido(pedido);
    }

    @Override
    public void confirmarPagamento(Pedido pedido, boolean pagamentoProcessado)
            throws Exception {
        // TODO: falta implementar
    }

    @Override
    public boolean consultarStatusPagamento(Pedido pedido) throws Exception {
        // TODO: falta implementar
        return true;
    }

    @Override
    public List<Pedido> listarPedidos() throws Exception {
        return gateway.listarPedidos();
    }

}

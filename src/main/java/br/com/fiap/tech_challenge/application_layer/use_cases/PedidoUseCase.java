package br.com.fiap.tech_challenge.application_layer.use_cases;

import java.util.List;

import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IPedidoGateway;
import br.com.fiap.tech_challenge.application_layer.interfaces.use_cases.IPedidoUseCase;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;
import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.StatusPedidoEnum;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesExceptions;

public class PedidoUseCase implements IPedidoUseCase {

    // Atributos
    private IPedidoGateway gateway;

    // Construtor
    public PedidoUseCase(IPedidoGateway gateway) {
        this.gateway = gateway;
    }

    // Métodos
    @Override
    public void atualizarStatus(Pedido pedido) throws BusinessRulesExceptions {
        pedido.atualizarStatusPedido();
        gateway.atualizarPedido(pedido);
    }

    @Override
    public void confirmarPagamento(Pedido pedido, boolean pagamentoProcessado) {
        // melhorar isso aqui
    }

    @Override
    public boolean consultarStatusPagamento(Pedido pedido) {
        // vai mudar
        return true;
    }

    @Override
    public void fazerCheckout(Pedido pedido) throws BusinessRulesExceptions {
        pedido.fazerCheckout();
        gateway.gravarPedido(pedido);
    }

    @Override
    public List<Pedido> listarPedidos() {
        return gateway.listarPedidosAbertos();
    }

}

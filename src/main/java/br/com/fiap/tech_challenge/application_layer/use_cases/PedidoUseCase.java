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
    public void atualizarStatus(Pedido pedido) throws Exception {
        try {
            pedido.atualizarStatusPedido();
            gateway.atualizarPedido(pedido);
        } catch (Exception e) {
            String msg = "Erro ao atualizar o status do pedido! ";
            throw new Exception(msg + e.getMessage());
        }
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
    public void fazerCheckout(Pedido pedido) throws Exception {
        try {
            pedido.fazerCheckout();
            gateway.gravarPedido(pedido);
        } catch (Exception e) {
            String msg = "Erro ao fazer o checkout! ";
            throw new Exception(msg + e.getMessage());
        }
    }

    @Override
    public List<Pedido> listarPedidos() throws Exception {
        try {
            return gateway.listarPedidos();
        } catch (Exception e) {
            String msg = "Erro ao listar os pedidos! ";
            throw new Exception(msg + e.getMessage());
        }
    }

}

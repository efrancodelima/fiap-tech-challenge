package br.com.fiap.tech_challenge.application_layer.use_cases;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IPedidoGateway;
import br.com.fiap.tech_challenge.application_layer.interfaces.use_cases.IPedidoUseCase;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;
import br.com.fiap.tech_challenge.domain_layer.business_entities.StatusPagamento;
import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.StatusPedidoEnum;

public class PedidoUseCase implements IPedidoUseCase {

    // Atributos
    private IPedidoGateway gateway;

    // Construtor
    public PedidoUseCase(IPedidoGateway gateway) {
        this.gateway = gateway;
    }

    // Métodos públicos
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

    @Override
    public List<Pedido> listarPedidos() throws Exception {
        List<Pedido> pedidos = gateway.buscarPedidos();
        return filtrarEOrdenarPedidos(pedidos);
    }

    // Métodos privados
    private List<Pedido> filtrarEOrdenarPedidos(List<Pedido> pedidos) {
        return pedidos.stream()
                .filter(p -> p.getStatusPedido().getStatus() != StatusPedidoEnum.AGUARDANDO_CHECKOUT &&
                        p.getStatusPedido().getStatus() != StatusPedidoEnum.FINALIZADO)
                .sorted(Comparator.comparing((Pedido p) -> p.getStatusPedido().getStatus(),
                        Comparator.comparingInt(this::getStatusOrder))
                        .thenComparing(p -> p.getStatusPedido().getDataHora()))
                .collect(Collectors.toList());
    }

    private int getStatusOrder(StatusPedidoEnum status) {
        switch (status) {
            case PRONTO:
                return 1;
            case EM_PREPARACAO:
                return 2;
            case RECEBIDO:
                return 3;
            default:
                throw new IllegalArgumentException("Status inválido: " + status);
        }
    }

}

package br.com.fiap.tech_challenge.application_layer.use_cases;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IPedidoGateway;
import br.com.fiap.tech_challenge.application_layer.use_cases.interfaces.IPedidoUseCase;
import br.com.fiap.tech_challenge.business_layer.entities.Pedido;
import br.com.fiap.tech_challenge.business_layer.entities.StatusPagamento;
import br.com.fiap.tech_challenge.business_layer.entities.enums.StatusPedidoEnum;

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

        Validar.notNull(pedido, EnumApplicationExceptions.PEDIDO_NULO);

        pedido.fazerCheckout();
        return gateway.gravarPedido(pedido);
    }

    @Override
    public Pedido atualizarStatusPedido(Long numeroPedido) throws Exception {

        validarNumeroPedido(numeroPedido);

        Pedido pedido = gateway.buscarPedido(numeroPedido);
        Validar.notNull(pedido, EnumNotFoundExceptions.PEDIDO_NAO_ENCONTRADO);

        pedido.atualizarStatusPedido();
        gateway.atualizarPedido(pedido);
        return pedido;
    }

    @Override
    public StatusPagamento consultarStatusPagamento(Long numeroPedido) throws Exception {

        Validar.notNull(numeroPedido, EnumApplicationExceptions.PEDIDO_NUMERO_NULO);

        Pedido pedido = gateway.buscarPedido(numeroPedido);
        Validar.notNull(pedido, EnumNotFoundExceptions.PEDIDO_NAO_ENCONTRADO);

        return pedido.getStatusPagamento();
    }

    @Override
    public List<Pedido> listarPedidos() throws Exception {

        List<Pedido> pedidos = gateway.buscarTodosOsPedidos();
        Validar.listNotEmpty(pedidos, EnumNotFoundExceptions.PEDIDO_LISTA_VAZIA);

        return filtrarEOrdenarPedidos(pedidos);
    }

    @Override
    public void atualizarStatusPagamento(StatusPagamento statusPagamento) throws Exception {

        Validar.notNull(statusPagamento, EnumApplicationExceptions.STATUS_PAGAMENTO_NULO);

        Pedido pedido = gateway.buscarPedidoPeloCodigoPagamento(statusPagamento.getCodigo());
        Validar.notNull(pedido, EnumNotFoundExceptions.PEDIDO_NAO_ENCONTRADO);

        pedido.setStatusPagamento(statusPagamento);
        gateway.atualizarPedido(pedido);
    }

    // Métodos privados
    private void validarNumeroPedido(Long numeroPedido) throws Exception {

        Validar.notNull(numeroPedido, EnumApplicationExceptions.PEDIDO_NUMERO_NULO);
        Validar.maiorQueZero(numeroPedido, EnumApplicationExceptions.PEDIDO_NUMERO_MIN);
    }

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

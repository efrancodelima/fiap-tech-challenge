package br.com.fiap.tech_challenge.application_layer.use_cases.pedido;

import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IPedidoGateway;
import br.com.fiap.tech_challenge.application_layer.services.Validar;
import br.com.fiap.tech_challenge.business_layer.entities.Pedido;
import br.com.fiap.tech_challenge.business_layer.entities.StatusPagamento;

public final class AtualizarStatusPagamento {

    public static void atualizar(IPedidoGateway gateway, StatusPagamento statusPagamento)
            throws Exception {

        Validar.notNull(statusPagamento, EnumApplicationExceptions.PAGAMENTO_STATUS_NULO);

        Pedido pedido = gateway.buscarPedidoPeloCodigoPagamento(statusPagamento.getCodigo());
        System.out.println("\n" + statusPagamento.getCodigo());
        Validar.notNull(pedido, EnumNotFoundExceptions.PEDIDO_NAO_ENCONTRADO);

        pedido.setStatusPagamento(statusPagamento);
        gateway.atualizarPedido(pedido);
    }

}

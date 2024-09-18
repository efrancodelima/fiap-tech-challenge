package br.com.fiap.tech_challenge.application_layer.use_cases.pedido;

import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IPedidoGateway;
import br.com.fiap.tech_challenge.application_layer.services.Validar;
import br.com.fiap.tech_challenge.business_layer.entities.Pedido;
import br.com.fiap.tech_challenge.business_layer.entities.StatusPagamento;

public final class ConsultarStatusPagamento {

    public static StatusPagamento consultar(IPedidoGateway gateway, Long numeroPedido) throws Exception {

        Validar.notNull(numeroPedido, EnumApplicationExceptions.PEDIDO_NUMERO_NULO);

        Pedido pedido = gateway.buscarPedido(numeroPedido);
        Validar.notNull(pedido, EnumNotFoundExceptions.PEDIDO_NAO_ENCONTRADO);

        return pedido.getStatusPagamento();
    }

}
